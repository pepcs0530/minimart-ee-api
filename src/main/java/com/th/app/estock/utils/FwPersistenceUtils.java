package com.th.app.estock.utils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;
import javax.persistence.Parameter;
import javax.persistence.Query;

import org.apache.commons.beanutils.PropertyUtils;

import com.th.app.estock.bean.TbProductTypeBean;
import com.th.app.estock.response.FwResponseEntity;
import com.th.app.estock.response.Paginator;

public class FwPersistenceUtils {

	protected static String getSelectClauseForAutomapper(String sql) {
		String sqlColumn = sql.toLowerCase();

		sqlColumn = sqlColumn.trim();

		sqlColumn = sqlColumn.replaceFirst("distinct", " ");

		sqlColumn = sqlColumn.replaceAll(",", " , ");

		sqlColumn = sqlColumn.replaceAll("[\t\r\n]", " ");

		sqlColumn = sqlColumn
				.replaceAll("(?i)extract *\\( *(day|month|year|hour|minute|second) +from +[a-zA-Z0-9_\\-]*+ *\\)", "");

		String[] sqls = sqlColumn.split(" ");
		int countSelect = 0;
		int countFrom = 0;
		int countC = 0;
		StringBuilder sqlr = new StringBuilder();

		boolean isAppend = true;

		for (int i = 0; i < sqls.length; i++) {
			isAppend = true;
			String sub = sqls[i].trim();
			if (!"".equals(sub)) {
				if (("select".equals(sub)) || ("(select".equals(sub)) || ("select(".equals(sub))
						|| ("(select(".equals(sub))) {
					countSelect++;
				}
				if (("from".equals(sub)) || ("(from".equals(sub)) || ("from(".equals(sub)) || ("(from(".equals(sub))) {
					countFrom++;
				}

				if (sub.indexOf("(") >= 0) {
					for (int j = 0; j < sub.length(); j++) {
						if (sub.charAt(j) == '(') {
							countC++;
						}
					}
				}
				if (sub.indexOf(")") >= 0) {
					for (int j = 0; j < sub.length(); j++) {
						if (sub.charAt(j) == ')') {
							countC--;
						}

					}

				}

				if ((countC == 0) && (sub.indexOf("(") >= 0) && (sub.indexOf(")") >= 0)) {
					isAppend = false;
				}
				if ((countC == 0) && (isAppend)) {
					sqlr.append(sqls[i]);
					sqlr.append(" ");
				}
				if (countSelect == countFrom)
					break;
			}
		}
		return sqlr.toString();
	}

	protected static String convertDbColumnToAttribute(String column) {
		column = column.toLowerCase();

		String[] names = column.split("_");
		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < names.length; i++) {
			String value = names[i];

			if (i == 0) {
				builder.append(value);
			} else {
				String begin = value.substring(0, 1);
				begin = begin.toUpperCase();
				builder.append(begin);
				builder.append(value.substring(1, value.length()));
			}
		}

		return builder.toString();
	}

	
	public   static <T> List<T> querySql(EntityManager entityManager, String sql, Class<T> clazz, Object request) {

		try {
			Query query = createNativeQuery(entityManager, sql);
			
			setParameters(query, request);
			return autoMapper(sql, clazz, query.getResultList());
		} finally {
		}

	}
	
	public   static <T> List<T> querySqlLazy(EntityManager entityManager, String sql, Class<T> clazz, Object request,Integer offset,Integer limit ) {
		try {
			Query query = createNativeQuery(entityManager, sql);
			query.setFirstResult(offset);
			query.setMaxResults(limit);
			setParameters(query, request);
			return autoMapper(sql, clazz, query.getResultList());
		} finally {
		}

	}

	protected static <T> List<T> autoMapper(String sql, Class<T> clazz, List<Object[]> resultList) {
		List results = new ArrayList();
		try {
			String sqlColumn = getSelectClauseForAutomapper(sql);

			int index = sqlColumn.lastIndexOf("from ");

			sqlColumn = sqlColumn.substring(0, index);
			sqlColumn = sqlColumn.trim();

			sqlColumn = sqlColumn.substring(7, sqlColumn.length());

			String[] columns = sqlColumn.split(",");
			String[] attributes = new String[columns.length];

			for (int i = 0; i < columns.length; i++) {
				columns[i] = columns[i].trim();

				if (columns[i].indexOf(' ') > 0) {
					String[] alias = columns[i].split(" ");

					String aliasName = alias[(alias.length - 1)].trim();

					aliasName = aliasName.replaceAll("\\'", "");

					columns[i] = aliasName.replace("#", ".");
				} else if (columns[i].indexOf('.') > 0) {
					String[] col = columns[i].split("\\.");

					columns[i] = col[(col.length - 1)].trim();
				}

				String attribute = convertDbColumnToAttribute(columns[i]);
				attributes[i] = attribute;
			}

			if (clazz.isAssignableFrom(String.class)) {
				for (int i = 0; i < resultList.size(); i++) {
					Object newValue = resultList.get(i);
					results.add(String.valueOf(newValue));
				}
			} else if (clazz.isAssignableFrom(Integer.class)) {
				for (int i = 0; i < resultList.size(); i++) {
					Object newValue = resultList.get(i);
					results.add((Integer) newValue);
				}
			} else if (clazz.isAssignableFrom(Long.class)) {
				for (int i = 0; i < resultList.size(); i++) {
					Object newValue = resultList.get(i);
					results.add((Long) newValue);
				}
			} else if (clazz.isAssignableFrom(BigDecimal.class)) {
				for (int i = 0; i < resultList.size(); i++) {
					Object newValue = resultList.get(i);
					results.add((BigDecimal) newValue);
				}
			} else {
				Object newValue;
				if (clazz.isAssignableFrom(Date.class)) {
					for (int i = 0; i < resultList.size(); i++) {
						newValue = resultList.get(i);
						results.add((Date) newValue);
					}

				} else if ((resultList != null) && (resultList.size() > 0))
					if ((resultList.get(0) instanceof Object[]))
						for (Object[] values : resultList) {
							int startIndex = 0;

							Object object = clazz.newInstance();

							for (int i = startIndex; i < attributes.length; i++) {
								setAttributeValue(object, attributes[i], values[i]);

								// if (columnCallbackHandler != null) {
								// columnCallbackHandler.processColumn(object,
								// attributes[i], values[i]);
								// }

							}

							setFieldDefault(object, values, attributes, Integer.valueOf(startIndex));

							results.add(object);
						}
					else
						// for (newValue = resultList.iterator();
						// newValue.hasNext(); )

						for (Object value : resultList)

						{
							// Object value = newValue.next();
							int startIndex = 0;

							Object object = clazz.newInstance();

							for (int i = startIndex; i < attributes.length; i++) {
								setAttributeValue(object, attributes[i], value);
								if ((value != null) && ((value instanceof Number))) {
									Number val = (Number) value;
									setAttributeValue(object, attributes[i], Long.valueOf(val.longValue()));
								}

							}

							results.add(object);
						}
			}
		} catch (Exception ex) {
			// this.log.error("เกิดข้อผิดพลาด auto mapper {}", ex);
		}
		return results;
	}

	protected static void setFieldDefault(Object row, Object[] values, String[] attributes, Integer startIndex)
			throws Exception {
		Method[] methods = row.getClass().getMethods();
		for (int i = 0; i < methods.length; i++) {
			Method method = methods[i];

			if ("setRightField".equals(method.getName())) {
				// DefaultRightField rightField =
				// (DefaultRightField)DefaultRightField.class.newInstance();
				// setRightFieldAllowAll(rightField);
				// method.invoke(row, new Object[] { rightField });
			}
		}
	}

	protected static Query createNativeQuery(EntityManager entityManager, String sql) {
		return entityManager.createNativeQuery(sql);
	}

	protected String getSelectClause(String sql) {
		Pattern regex = Pattern.compile("(?i)select(.*?)\\sfrom");
		Matcher regexMatcher = regex.matcher(sql);
		if (regexMatcher.find()) {
			return regexMatcher.group();
		}
		return sql;
	}

	protected static void setAttributeValue(Object object, String name, Object value) throws Exception {
		try {
			if ((value != null) || (name.indexOf('.') > 0)) {
				if (name.indexOf('.') > 0) {
					Class clazz = object.getClass();

					if (name.startsWith("'")) {
						name = name.substring(1);
					}
					if (name.endsWith("'")) {
						name = name.substring(0, name.length() - 1);
					}

					String replace = name.replace(".", "#");
					String[] arr = replace.split("#");
					String chain = "";
					for (int i = 0; i < arr.length - 1; i++) {
						if (chain.length() > 0)
							chain = chain + "." + arr[i];
						else {
							chain = arr[i];
						}

						if (PropertyUtils.isReadable(object, chain)) {
							Object childObject = PropertyUtils.getProperty(object, chain);
							if (childObject == null) {
								Class returnTypeClass = null;
								String first = arr[i].substring(0, 1).toUpperCase();
								String className = first + arr[i].substring(1);
								Object motherObject = null;
								if (i == 0) {
									returnTypeClass = clazz.getMethod("get" + className, new Class[0]).getReturnType();
								} else {
									motherObject = PropertyUtils.getProperty(object, arr[(i - 1)]);
									returnTypeClass = motherObject.getClass().getMethod("get" + className, new Class[0])
											.getReturnType();
								}

								if (returnTypeClass != null) {
									childObject = returnTypeClass.newInstance();
									setProperty(object, chain, childObject);
								}
							}
						}
					}

				}

				setProperty(object, name, value);
			}
		} catch (NoSuchMethodException e) {
			// this.log.trace("ไม่มี Attribute ที่ชื่อว่า [{}] ให้ทำงานต่อไป",
			// name);
		} catch (IllegalArgumentException e) {
			// this.log.debug("ประเภทของ Attribute [{}] ไม่ตรง ให้ทำงานต่อไป",
			// name);
		} catch (IntrospectionException e) {
			// this.log.trace("ไม่มี Attribute ที่ชื่อว่า [{}] ให้ทำงานต่อไป",
			// name);
		} catch (Exception ex) {
			throw ex;
		}
	}

	protected  static void setProperty(Object object, String name, Object value) throws Exception {
		Class clazz = object.getClass();
		PropertyDescriptor writer = null;

		if (name.indexOf('.') > 0) {
			String objectName = name.substring(0, name.lastIndexOf('.'));

			if (objectName.startsWith("'")) {
				objectName = objectName.substring(1);
			}
			String attributeName = name.substring(name.lastIndexOf('.') + 1);

			if (attributeName.endsWith("'")) {
				attributeName = attributeName.substring(0, attributeName.length() - 1);
			}

			Object childObject = PropertyUtils.getProperty(object, objectName);
			writer = new PropertyDescriptor(attributeName, childObject.getClass());
		} else {
			writer = new PropertyDescriptor(name, clazz);
		}

		Class[] clazzs = writer.getWriteMethod().getParameterTypes();
		Class type = clazzs[0];

		if ((value != null) && (type.getClass() != value.getClass())) {
			Class valueType = value.getClass();

			if ((type.isAssignableFrom(Integer.class)) && (Number.class.isAssignableFrom(valueType))) {
				Number newValue = (Number) value;
				value = Integer.valueOf(newValue.intValue());
			} else if ((type.isAssignableFrom(Long.class)) && (Number.class.isAssignableFrom(valueType))) {
				Number newValue = (Number) value;
				value = Long.valueOf(newValue.longValue());
			} else if ((type.isAssignableFrom(Double.class)) && (Number.class.isAssignableFrom(valueType))) {
				Number newValue = (Number) value;
				value = Double.valueOf(newValue.doubleValue());
			} else if ((type.isAssignableFrom(BigDecimal.class)) && (Number.class.isAssignableFrom(valueType))) {
				Number newValue = (Number) value;
				value = BigDecimal.valueOf(newValue.doubleValue());
			} else if ((type.isAssignableFrom(String.class)) && (Number.class.isAssignableFrom(valueType))) {
				Number newValue = (Number) value;
				value = newValue.toString();
			} else if ((type.isAssignableFrom(String.class)) && (valueType.isAssignableFrom(Character.class))) {
				Character newValue = (Character) value;
				value = newValue.toString();
			} else if (type.isEnum()) {
				int ordinalValue = Integer.valueOf(value.toString()).intValue();
				if (ordinalValue < type.getEnumConstants().length) {
					value = type.getEnumConstants()[ordinalValue];
				}

			} else {
				// this.log.debug("log :{} type", type);
			}

		}

		try {
			PropertyUtils.setProperty(object, name, value);
		} catch (NoSuchMethodException e) {
			String patternToCheckOnlyOneLowercase = "^[a-z][A-Z]";
			Pattern r = Pattern.compile(patternToCheckOnlyOneLowercase);
			Matcher m = r.matcher(name);
			if (m.find()) {
				String newName = Character.toUpperCase(name.charAt(0)) + name.substring(1);
				PropertyUtils.setProperty(object, newName, value);
			} else {
				throw e;
			}
		}
	}

	protected static void setParameter(Query query, Object criteria, String parameterName) {
		Object value = null;
		try {
			if ("currentDate".equals(parameterName)) {
				setQueryParameter(query, new Date(), "currentDate");
				return;
			}

			if (criteria == null) {
				setQueryParameter(query, parameterName);
				// this.log.debug("set parameter [{}] to [null]",
				// parameterName);
			} else {
				if (((criteria instanceof String)) || ((criteria instanceof Number))
						|| ((criteria instanceof Collection)) || (criteria.getClass().isArray()))
					value = criteria;
				else if ((criteria instanceof Map)) {
					value = ((Map) criteria).get(parameterName);
				} else {
					value = PropertyUtils.getProperty(criteria, parameterName);
				}

				setValue(query, value, parameterName);
			}
		} catch (Exception e) {
			// this.log.error("ข้อผิดพลาดเกิดจาก การใช้งาน Query
			// ของเพื่อพยายามกำหนดค่าพารามิเตอร์ชื่อ [{}] มีค่าเป็น [{}]
			// เข้าไปใน Query", new Object[] { parameterName, value }, e);
		}
	}

	protected  static void setQueryParameter(Query query, Object value, String parameterName) {
		// this.log.debug("set parameter [{}] = [{}]", parameterName, value);
		query.setParameter(parameterName, value);
	}

	protected static void setQueryParameter(Query query, String parameterName) {
		setQueryParameter(query, null, parameterName);
	}

	protected static void setValue(Query query, Object value, String parameterName) throws Exception {
		boolean isNull = false;

		if (value == null) {
			isNull = true;
		} else if ((value instanceof Collection)) {
			Collection collection = (Collection) value;
			if (collection.isEmpty()) {
				setQueryParameter(query, parameterName);
			}
		} else if (value.getClass().isArray()) {
			Object[] values = (Object[]) value;
			if (values.length == 0) {
				setQueryParameter(query, parameterName);
			}
		} else if (value.getClass().isEnum()) {
			String valueStr = value.toString();
			for (Object enumConstant : value.getClass().getEnumConstants())
				if (valueStr.equals(enumConstant.toString())) {
					value = enumConstant;
					break;
				}
		} else {
			// this.log.debug("log : {} type class " + value.getClass());
		}

		if (isNull) {
			setQueryParameter(query, parameterName);
			// this.log.debug("กำหนดค่าคิวรีพารามิเตอร์ :" + parameterName + " =
			// null ");
		} else {
			setQueryParameter(query, value, parameterName);
			// this.log.debug("กำหนดค่าคิวรีพารามิเตอร์ :" + parameterName + " =
			// [" + value + "]");
		}
	}

	protected static void setParameters(Query query, Object criteria) {
		// Set parameters1 = query.getParameters();
		for (Parameter parameter : query.getParameters())
			setParameter(query, criteria, parameter.getName());
	}
	
	@SuppressWarnings("unchecked")
	public static <T> List<T> fwQueryWithPaginator(EntityManager entityManager, String sql,
			Class<T> class1, Map<String, Object> conditions, int offset, int limit, FwResponseEntity<T> response) {
		//FwResponseEntity<T> response = new FwResponseEntity<>();
		StringBuilder countSql = new StringBuilder();
		List<T> results = new ArrayList<>();
		Paginator paginator = new Paginator();
		int count = 0;
		
		try {
			countSql.append(" SELECT COUNT(*) as count FROM ( ");
			countSql.append(sql);
			countSql.append(" ) count ");
			
			//จะดึง datacount เฉพาะกดค้นหาครั้งแรกเท่านั้น
			/*if(totalRecords==0) {
				//หาจำนวน record ทั้งหมด
				Query countQuery = createNativeQuery(entityManager, countSql.toString());
				setParameters(countQuery, conditions);
				
				BigInteger _count = (BigInteger) countQuery.getResultList().get(0);
				count = new Integer(_count.intValue());
				//count = ((BigDecimal) countQuery.getResultList().get(0)).intValue();
			}*/
			
			Query countQuery = createNativeQuery(entityManager, countSql.toString());
			setParameters(countQuery, conditions);
			
			BigInteger _count = (BigInteger) countQuery.getResultList().get(0);
			count = new Integer(_count.intValue());
			//count = ((BigDecimal) countQuery.getResultList().get(0)).intValue();
			paginator.setCount(count);
		
			//query data 
			Query query = createNativeQuery(entityManager, sql);
			query.setFirstResult(offset != 0 ? offset : 0);
			query.setMaxResults(limit != 0 ? limit : 5);
			setParameters(query, conditions); 
			results =  autoMapper(sql, class1, query.getResultList());
			
			if(paginator != null) {
				paginator.setOffset(offset != 0 ? offset : 0);
				paginator.setLimit(limit != 0 ? limit : 5);
				
				//จะดึง datacount เฉพาะกดค้นหาครั้งแรกเท่านั้น
				/*if(totalRecords==0) {
					response2.setCount(count);
				}else {
					response2.setCount(totalRecords);
				}*/
			}
			response.setResults(results);
			response.setPaginator(paginator);
			return results;
		}catch(Exception ex) {
			throw ex;
		} finally {
			
		}
	}

}
