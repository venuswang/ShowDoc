package cn.scau.edu.ssm.showdoc.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ProjectExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProjectExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andProjectnameIsNull() {
            addCriterion("projectname is null");
            return (Criteria) this;
        }

        public Criteria andProjectnameIsNotNull() {
            addCriterion("projectname is not null");
            return (Criteria) this;
        }

        public Criteria andProjectnameEqualTo(String value) {
            addCriterion("projectname =", value, "projectname");
            return (Criteria) this;
        }

        public Criteria andProjectnameNotEqualTo(String value) {
            addCriterion("projectname <>", value, "projectname");
            return (Criteria) this;
        }

        public Criteria andProjectnameGreaterThan(String value) {
            addCriterion("projectname >", value, "projectname");
            return (Criteria) this;
        }

        public Criteria andProjectnameGreaterThanOrEqualTo(String value) {
            addCriterion("projectname >=", value, "projectname");
            return (Criteria) this;
        }

        public Criteria andProjectnameLessThan(String value) {
            addCriterion("projectname <", value, "projectname");
            return (Criteria) this;
        }

        public Criteria andProjectnameLessThanOrEqualTo(String value) {
            addCriterion("projectname <=", value, "projectname");
            return (Criteria) this;
        }

        public Criteria andProjectnameLike(String value) {
            addCriterion("projectname like", value, "projectname");
            return (Criteria) this;
        }

        public Criteria andProjectnameNotLike(String value) {
            addCriterion("projectname not like", value, "projectname");
            return (Criteria) this;
        }

        public Criteria andProjectnameIn(List<String> values) {
            addCriterion("projectname in", values, "projectname");
            return (Criteria) this;
        }

        public Criteria andProjectnameNotIn(List<String> values) {
            addCriterion("projectname not in", values, "projectname");
            return (Criteria) this;
        }

        public Criteria andProjectnameBetween(String value1, String value2) {
            addCriterion("projectname between", value1, value2, "projectname");
            return (Criteria) this;
        }

        public Criteria andProjectnameNotBetween(String value1, String value2) {
            addCriterion("projectname not between", value1, value2, "projectname");
            return (Criteria) this;
        }

        public Criteria andProjectdescIsNull() {
            addCriterion("projectdesc is null");
            return (Criteria) this;
        }

        public Criteria andProjectdescIsNotNull() {
            addCriterion("projectdesc is not null");
            return (Criteria) this;
        }

        public Criteria andProjectdescEqualTo(String value) {
            addCriterion("projectdesc =", value, "projectdesc");
            return (Criteria) this;
        }

        public Criteria andProjectdescNotEqualTo(String value) {
            addCriterion("projectdesc <>", value, "projectdesc");
            return (Criteria) this;
        }

        public Criteria andProjectdescGreaterThan(String value) {
            addCriterion("projectdesc >", value, "projectdesc");
            return (Criteria) this;
        }

        public Criteria andProjectdescGreaterThanOrEqualTo(String value) {
            addCriterion("projectdesc >=", value, "projectdesc");
            return (Criteria) this;
        }

        public Criteria andProjectdescLessThan(String value) {
            addCriterion("projectdesc <", value, "projectdesc");
            return (Criteria) this;
        }

        public Criteria andProjectdescLessThanOrEqualTo(String value) {
            addCriterion("projectdesc <=", value, "projectdesc");
            return (Criteria) this;
        }

        public Criteria andProjectdescLike(String value) {
            addCriterion("projectdesc like", value, "projectdesc");
            return (Criteria) this;
        }

        public Criteria andProjectdescNotLike(String value) {
            addCriterion("projectdesc not like", value, "projectdesc");
            return (Criteria) this;
        }

        public Criteria andProjectdescIn(List<String> values) {
            addCriterion("projectdesc in", values, "projectdesc");
            return (Criteria) this;
        }

        public Criteria andProjectdescNotIn(List<String> values) {
            addCriterion("projectdesc not in", values, "projectdesc");
            return (Criteria) this;
        }

        public Criteria andProjectdescBetween(String value1, String value2) {
            addCriterion("projectdesc between", value1, value2, "projectdesc");
            return (Criteria) this;
        }

        public Criteria andProjectdescNotBetween(String value1, String value2) {
            addCriterion("projectdesc not between", value1, value2, "projectdesc");
            return (Criteria) this;
        }

        public Criteria andAuthornameIsNull() {
            addCriterion("authorname is null");
            return (Criteria) this;
        }

        public Criteria andAuthornameIsNotNull() {
            addCriterion("authorname is not null");
            return (Criteria) this;
        }

        public Criteria andAuthornameEqualTo(String value) {
            addCriterion("authorname =", value, "authorname");
            return (Criteria) this;
        }

        public Criteria andAuthornameNotEqualTo(String value) {
            addCriterion("authorname <>", value, "authorname");
            return (Criteria) this;
        }

        public Criteria andAuthornameGreaterThan(String value) {
            addCriterion("authorname >", value, "authorname");
            return (Criteria) this;
        }

        public Criteria andAuthornameGreaterThanOrEqualTo(String value) {
            addCriterion("authorname >=", value, "authorname");
            return (Criteria) this;
        }

        public Criteria andAuthornameLessThan(String value) {
            addCriterion("authorname <", value, "authorname");
            return (Criteria) this;
        }

        public Criteria andAuthornameLessThanOrEqualTo(String value) {
            addCriterion("authorname <=", value, "authorname");
            return (Criteria) this;
        }

        public Criteria andAuthornameLike(String value) {
            addCriterion("authorname like", value, "authorname");
            return (Criteria) this;
        }

        public Criteria andAuthornameNotLike(String value) {
            addCriterion("authorname not like", value, "authorname");
            return (Criteria) this;
        }

        public Criteria andAuthornameIn(List<String> values) {
            addCriterion("authorname in", values, "authorname");
            return (Criteria) this;
        }

        public Criteria andAuthornameNotIn(List<String> values) {
            addCriterion("authorname not in", values, "authorname");
            return (Criteria) this;
        }

        public Criteria andAuthornameBetween(String value1, String value2) {
            addCriterion("authorname between", value1, value2, "authorname");
            return (Criteria) this;
        }

        public Criteria andAuthornameNotBetween(String value1, String value2) {
            addCriterion("authorname not between", value1, value2, "authorname");
            return (Criteria) this;
        }

        public Criteria andProjectpasswordIsNull() {
            addCriterion("projectpassword is null");
            return (Criteria) this;
        }

        public Criteria andProjectpasswordIsNotNull() {
            addCriterion("projectpassword is not null");
            return (Criteria) this;
        }

        public Criteria andProjectpasswordEqualTo(String value) {
            addCriterion("projectpassword =", value, "projectpassword");
            return (Criteria) this;
        }

        public Criteria andProjectpasswordNotEqualTo(String value) {
            addCriterion("projectpassword <>", value, "projectpassword");
            return (Criteria) this;
        }

        public Criteria andProjectpasswordGreaterThan(String value) {
            addCriterion("projectpassword >", value, "projectpassword");
            return (Criteria) this;
        }

        public Criteria andProjectpasswordGreaterThanOrEqualTo(String value) {
            addCriterion("projectpassword >=", value, "projectpassword");
            return (Criteria) this;
        }

        public Criteria andProjectpasswordLessThan(String value) {
            addCriterion("projectpassword <", value, "projectpassword");
            return (Criteria) this;
        }

        public Criteria andProjectpasswordLessThanOrEqualTo(String value) {
            addCriterion("projectpassword <=", value, "projectpassword");
            return (Criteria) this;
        }

        public Criteria andProjectpasswordLike(String value) {
            addCriterion("projectpassword like", value, "projectpassword");
            return (Criteria) this;
        }

        public Criteria andProjectpasswordNotLike(String value) {
            addCriterion("projectpassword not like", value, "projectpassword");
            return (Criteria) this;
        }

        public Criteria andProjectpasswordIn(List<String> values) {
            addCriterion("projectpassword in", values, "projectpassword");
            return (Criteria) this;
        }

        public Criteria andProjectpasswordNotIn(List<String> values) {
            addCriterion("projectpassword not in", values, "projectpassword");
            return (Criteria) this;
        }

        public Criteria andProjectpasswordBetween(String value1, String value2) {
            addCriterion("projectpassword between", value1, value2, "projectpassword");
            return (Criteria) this;
        }

        public Criteria andProjectpasswordNotBetween(String value1, String value2) {
            addCriterion("projectpassword not between", value1, value2, "projectpassword");
            return (Criteria) this;
        }

        public Criteria andSortidIsNull() {
            addCriterion("sortid is null");
            return (Criteria) this;
        }

        public Criteria andSortidIsNotNull() {
            addCriterion("sortid is not null");
            return (Criteria) this;
        }

        public Criteria andSortidEqualTo(Integer value) {
            addCriterion("sortid =", value, "sortid");
            return (Criteria) this;
        }

        public Criteria andSortidNotEqualTo(Integer value) {
            addCriterion("sortid <>", value, "sortid");
            return (Criteria) this;
        }

        public Criteria andSortidGreaterThan(Integer value) {
            addCriterion("sortid >", value, "sortid");
            return (Criteria) this;
        }

        public Criteria andSortidGreaterThanOrEqualTo(Integer value) {
            addCriterion("sortid >=", value, "sortid");
            return (Criteria) this;
        }

        public Criteria andSortidLessThan(Integer value) {
            addCriterion("sortid <", value, "sortid");
            return (Criteria) this;
        }

        public Criteria andSortidLessThanOrEqualTo(Integer value) {
            addCriterion("sortid <=", value, "sortid");
            return (Criteria) this;
        }

        public Criteria andSortidIn(List<Integer> values) {
            addCriterion("sortid in", values, "sortid");
            return (Criteria) this;
        }

        public Criteria andSortidNotIn(List<Integer> values) {
            addCriterion("sortid not in", values, "sortid");
            return (Criteria) this;
        }

        public Criteria andSortidBetween(Integer value1, Integer value2) {
            addCriterion("sortid between", value1, value2, "sortid");
            return (Criteria) this;
        }

        public Criteria andSortidNotBetween(Integer value1, Integer value2) {
            addCriterion("sortid not between", value1, value2, "sortid");
            return (Criteria) this;
        }

        public Criteria andPstatuIsNull() {
            addCriterion("pstatu is null");
            return (Criteria) this;
        }

        public Criteria andPstatuIsNotNull() {
            addCriterion("pstatu is not null");
            return (Criteria) this;
        }

        public Criteria andPstatuEqualTo(Integer value) {
            addCriterion("pstatu =", value, "pstatu");
            return (Criteria) this;
        }

        public Criteria andPstatuNotEqualTo(Integer value) {
            addCriterion("pstatu <>", value, "pstatu");
            return (Criteria) this;
        }

        public Criteria andPstatuGreaterThan(Integer value) {
            addCriterion("pstatu >", value, "pstatu");
            return (Criteria) this;
        }

        public Criteria andPstatuGreaterThanOrEqualTo(Integer value) {
            addCriterion("pstatu >=", value, "pstatu");
            return (Criteria) this;
        }

        public Criteria andPstatuLessThan(Integer value) {
            addCriterion("pstatu <", value, "pstatu");
            return (Criteria) this;
        }

        public Criteria andPstatuLessThanOrEqualTo(Integer value) {
            addCriterion("pstatu <=", value, "pstatu");
            return (Criteria) this;
        }

        public Criteria andPstatuIn(List<Integer> values) {
            addCriterion("pstatu in", values, "pstatu");
            return (Criteria) this;
        }

        public Criteria andPstatuNotIn(List<Integer> values) {
            addCriterion("pstatu not in", values, "pstatu");
            return (Criteria) this;
        }

        public Criteria andPstatuBetween(Integer value1, Integer value2) {
            addCriterion("pstatu between", value1, value2, "pstatu");
            return (Criteria) this;
        }

        public Criteria andPstatuNotBetween(Integer value1, Integer value2) {
            addCriterion("pstatu not between", value1, value2, "pstatu");
            return (Criteria) this;
        }

        public Criteria andPdateIsNull() {
            addCriterion("pdate is null");
            return (Criteria) this;
        }

        public Criteria andPdateIsNotNull() {
            addCriterion("pdate is not null");
            return (Criteria) this;
        }

        public Criteria andPdateEqualTo(Date value) {
            addCriterionForJDBCDate("pdate =", value, "pdate");
            return (Criteria) this;
        }

        public Criteria andPdateNotEqualTo(Date value) {
            addCriterionForJDBCDate("pdate <>", value, "pdate");
            return (Criteria) this;
        }

        public Criteria andPdateGreaterThan(Date value) {
            addCriterionForJDBCDate("pdate >", value, "pdate");
            return (Criteria) this;
        }

        public Criteria andPdateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("pdate >=", value, "pdate");
            return (Criteria) this;
        }

        public Criteria andPdateLessThan(Date value) {
            addCriterionForJDBCDate("pdate <", value, "pdate");
            return (Criteria) this;
        }

        public Criteria andPdateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("pdate <=", value, "pdate");
            return (Criteria) this;
        }

        public Criteria andPdateIn(List<Date> values) {
            addCriterionForJDBCDate("pdate in", values, "pdate");
            return (Criteria) this;
        }

        public Criteria andPdateNotIn(List<Date> values) {
            addCriterionForJDBCDate("pdate not in", values, "pdate");
            return (Criteria) this;
        }

        public Criteria andPdateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("pdate between", value1, value2, "pdate");
            return (Criteria) this;
        }

        public Criteria andPdateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("pdate not between", value1, value2, "pdate");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}