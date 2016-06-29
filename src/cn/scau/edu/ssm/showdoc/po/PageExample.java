package cn.scau.edu.ssm.showdoc.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class PageExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PageExample() {
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

        public Criteria andPageidIsNull() {
            addCriterion("pageid is null");
            return (Criteria) this;
        }

        public Criteria andPageidIsNotNull() {
            addCriterion("pageid is not null");
            return (Criteria) this;
        }

        public Criteria andPageidEqualTo(Integer value) {
            addCriterion("pageid =", value, "pageid");
            return (Criteria) this;
        }

        public Criteria andPageidNotEqualTo(Integer value) {
            addCriterion("pageid <>", value, "pageid");
            return (Criteria) this;
        }

        public Criteria andPageidGreaterThan(Integer value) {
            addCriterion("pageid >", value, "pageid");
            return (Criteria) this;
        }

        public Criteria andPageidGreaterThanOrEqualTo(Integer value) {
            addCriterion("pageid >=", value, "pageid");
            return (Criteria) this;
        }

        public Criteria andPageidLessThan(Integer value) {
            addCriterion("pageid <", value, "pageid");
            return (Criteria) this;
        }

        public Criteria andPageidLessThanOrEqualTo(Integer value) {
            addCriterion("pageid <=", value, "pageid");
            return (Criteria) this;
        }

        public Criteria andPageidIn(List<Integer> values) {
            addCriterion("pageid in", values, "pageid");
            return (Criteria) this;
        }

        public Criteria andPageidNotIn(List<Integer> values) {
            addCriterion("pageid not in", values, "pageid");
            return (Criteria) this;
        }

        public Criteria andPageidBetween(Integer value1, Integer value2) {
            addCriterion("pageid between", value1, value2, "pageid");
            return (Criteria) this;
        }

        public Criteria andPageidNotBetween(Integer value1, Integer value2) {
            addCriterion("pageid not between", value1, value2, "pageid");
            return (Criteria) this;
        }

        public Criteria andPageprojectidIsNull() {
            addCriterion("pageprojectid is null");
            return (Criteria) this;
        }

        public Criteria andPageprojectidIsNotNull() {
            addCriterion("pageprojectid is not null");
            return (Criteria) this;
        }

        public Criteria andPageprojectidEqualTo(Integer value) {
            addCriterion("pageprojectid =", value, "pageprojectid");
            return (Criteria) this;
        }

        public Criteria andPageprojectidNotEqualTo(Integer value) {
            addCriterion("pageprojectid <>", value, "pageprojectid");
            return (Criteria) this;
        }

        public Criteria andPageprojectidGreaterThan(Integer value) {
            addCriterion("pageprojectid >", value, "pageprojectid");
            return (Criteria) this;
        }

        public Criteria andPageprojectidGreaterThanOrEqualTo(Integer value) {
            addCriterion("pageprojectid >=", value, "pageprojectid");
            return (Criteria) this;
        }

        public Criteria andPageprojectidLessThan(Integer value) {
            addCriterion("pageprojectid <", value, "pageprojectid");
            return (Criteria) this;
        }

        public Criteria andPageprojectidLessThanOrEqualTo(Integer value) {
            addCriterion("pageprojectid <=", value, "pageprojectid");
            return (Criteria) this;
        }

        public Criteria andPageprojectidIn(List<Integer> values) {
            addCriterion("pageprojectid in", values, "pageprojectid");
            return (Criteria) this;
        }

        public Criteria andPageprojectidNotIn(List<Integer> values) {
            addCriterion("pageprojectid not in", values, "pageprojectid");
            return (Criteria) this;
        }

        public Criteria andPageprojectidBetween(Integer value1, Integer value2) {
            addCriterion("pageprojectid between", value1, value2, "pageprojectid");
            return (Criteria) this;
        }

        public Criteria andPageprojectidNotBetween(Integer value1, Integer value2) {
            addCriterion("pageprojectid not between", value1, value2, "pageprojectid");
            return (Criteria) this;
        }

        public Criteria andPagesubprejectidIsNull() {
            addCriterion("pagesubprejectid is null");
            return (Criteria) this;
        }

        public Criteria andPagesubprejectidIsNotNull() {
            addCriterion("pagesubprejectid is not null");
            return (Criteria) this;
        }

        public Criteria andPagesubprejectidEqualTo(Integer value) {
            addCriterion("pagesubprejectid =", value, "pagesubprejectid");
            return (Criteria) this;
        }

        public Criteria andPagesubprejectidNotEqualTo(Integer value) {
            addCriterion("pagesubprejectid <>", value, "pagesubprejectid");
            return (Criteria) this;
        }

        public Criteria andPagesubprejectidGreaterThan(Integer value) {
            addCriterion("pagesubprejectid >", value, "pagesubprejectid");
            return (Criteria) this;
        }

        public Criteria andPagesubprejectidGreaterThanOrEqualTo(Integer value) {
            addCriterion("pagesubprejectid >=", value, "pagesubprejectid");
            return (Criteria) this;
        }

        public Criteria andPagesubprejectidLessThan(Integer value) {
            addCriterion("pagesubprejectid <", value, "pagesubprejectid");
            return (Criteria) this;
        }

        public Criteria andPagesubprejectidLessThanOrEqualTo(Integer value) {
            addCriterion("pagesubprejectid <=", value, "pagesubprejectid");
            return (Criteria) this;
        }

        public Criteria andPagesubprejectidIn(List<Integer> values) {
            addCriterion("pagesubprejectid in", values, "pagesubprejectid");
            return (Criteria) this;
        }

        public Criteria andPagesubprejectidNotIn(List<Integer> values) {
            addCriterion("pagesubprejectid not in", values, "pagesubprejectid");
            return (Criteria) this;
        }

        public Criteria andPagesubprejectidBetween(Integer value1, Integer value2) {
            addCriterion("pagesubprejectid between", value1, value2, "pagesubprejectid");
            return (Criteria) this;
        }

        public Criteria andPagesubprejectidNotBetween(Integer value1, Integer value2) {
            addCriterion("pagesubprejectid not between", value1, value2, "pagesubprejectid");
            return (Criteria) this;
        }

        public Criteria andPageprojectpasswordIsNull() {
            addCriterion("pageprojectpassword is null");
            return (Criteria) this;
        }

        public Criteria andPageprojectpasswordIsNotNull() {
            addCriterion("pageprojectpassword is not null");
            return (Criteria) this;
        }

        public Criteria andPageprojectpasswordEqualTo(String value) {
            addCriterion("pageprojectpassword =", value, "pageprojectpassword");
            return (Criteria) this;
        }

        public Criteria andPageprojectpasswordNotEqualTo(String value) {
            addCriterion("pageprojectpassword <>", value, "pageprojectpassword");
            return (Criteria) this;
        }

        public Criteria andPageprojectpasswordGreaterThan(String value) {
            addCriterion("pageprojectpassword >", value, "pageprojectpassword");
            return (Criteria) this;
        }

        public Criteria andPageprojectpasswordGreaterThanOrEqualTo(String value) {
            addCriterion("pageprojectpassword >=", value, "pageprojectpassword");
            return (Criteria) this;
        }

        public Criteria andPageprojectpasswordLessThan(String value) {
            addCriterion("pageprojectpassword <", value, "pageprojectpassword");
            return (Criteria) this;
        }

        public Criteria andPageprojectpasswordLessThanOrEqualTo(String value) {
            addCriterion("pageprojectpassword <=", value, "pageprojectpassword");
            return (Criteria) this;
        }

        public Criteria andPageprojectpasswordLike(String value) {
            addCriterion("pageprojectpassword like", value, "pageprojectpassword");
            return (Criteria) this;
        }

        public Criteria andPageprojectpasswordNotLike(String value) {
            addCriterion("pageprojectpassword not like", value, "pageprojectpassword");
            return (Criteria) this;
        }

        public Criteria andPageprojectpasswordIn(List<String> values) {
            addCriterion("pageprojectpassword in", values, "pageprojectpassword");
            return (Criteria) this;
        }

        public Criteria andPageprojectpasswordNotIn(List<String> values) {
            addCriterion("pageprojectpassword not in", values, "pageprojectpassword");
            return (Criteria) this;
        }

        public Criteria andPageprojectpasswordBetween(String value1, String value2) {
            addCriterion("pageprojectpassword between", value1, value2, "pageprojectpassword");
            return (Criteria) this;
        }

        public Criteria andPageprojectpasswordNotBetween(String value1, String value2) {
            addCriterion("pageprojectpassword not between", value1, value2, "pageprojectpassword");
            return (Criteria) this;
        }

        public Criteria andPagestatuIsNull() {
            addCriterion("pagestatu is null");
            return (Criteria) this;
        }

        public Criteria andPagestatuIsNotNull() {
            addCriterion("pagestatu is not null");
            return (Criteria) this;
        }

        public Criteria andPagestatuEqualTo(Integer value) {
            addCriterion("pagestatu =", value, "pagestatu");
            return (Criteria) this;
        }

        public Criteria andPagestatuNotEqualTo(Integer value) {
            addCriterion("pagestatu <>", value, "pagestatu");
            return (Criteria) this;
        }

        public Criteria andPagestatuGreaterThan(Integer value) {
            addCriterion("pagestatu >", value, "pagestatu");
            return (Criteria) this;
        }

        public Criteria andPagestatuGreaterThanOrEqualTo(Integer value) {
            addCriterion("pagestatu >=", value, "pagestatu");
            return (Criteria) this;
        }

        public Criteria andPagestatuLessThan(Integer value) {
            addCriterion("pagestatu <", value, "pagestatu");
            return (Criteria) this;
        }

        public Criteria andPagestatuLessThanOrEqualTo(Integer value) {
            addCriterion("pagestatu <=", value, "pagestatu");
            return (Criteria) this;
        }

        public Criteria andPagestatuIn(List<Integer> values) {
            addCriterion("pagestatu in", values, "pagestatu");
            return (Criteria) this;
        }

        public Criteria andPagestatuNotIn(List<Integer> values) {
            addCriterion("pagestatu not in", values, "pagestatu");
            return (Criteria) this;
        }

        public Criteria andPagestatuBetween(Integer value1, Integer value2) {
            addCriterion("pagestatu between", value1, value2, "pagestatu");
            return (Criteria) this;
        }

        public Criteria andPagestatuNotBetween(Integer value1, Integer value2) {
            addCriterion("pagestatu not between", value1, value2, "pagestatu");
            return (Criteria) this;
        }

        public Criteria andPagedateIsNull() {
            addCriterion("pagedate is null");
            return (Criteria) this;
        }

        public Criteria andPagedateIsNotNull() {
            addCriterion("pagedate is not null");
            return (Criteria) this;
        }

        public Criteria andPagedateEqualTo(Date value) {
            addCriterionForJDBCDate("pagedate =", value, "pagedate");
            return (Criteria) this;
        }

        public Criteria andPagedateNotEqualTo(Date value) {
            addCriterionForJDBCDate("pagedate <>", value, "pagedate");
            return (Criteria) this;
        }

        public Criteria andPagedateGreaterThan(Date value) {
            addCriterionForJDBCDate("pagedate >", value, "pagedate");
            return (Criteria) this;
        }

        public Criteria andPagedateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("pagedate >=", value, "pagedate");
            return (Criteria) this;
        }

        public Criteria andPagedateLessThan(Date value) {
            addCriterionForJDBCDate("pagedate <", value, "pagedate");
            return (Criteria) this;
        }

        public Criteria andPagedateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("pagedate <=", value, "pagedate");
            return (Criteria) this;
        }

        public Criteria andPagedateIn(List<Date> values) {
            addCriterionForJDBCDate("pagedate in", values, "pagedate");
            return (Criteria) this;
        }

        public Criteria andPagedateNotIn(List<Date> values) {
            addCriterionForJDBCDate("pagedate not in", values, "pagedate");
            return (Criteria) this;
        }

        public Criteria andPagedateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("pagedate between", value1, value2, "pagedate");
            return (Criteria) this;
        }

        public Criteria andPagedateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("pagedate not between", value1, value2, "pagedate");
            return (Criteria) this;
        }

        public Criteria andPagetitleIsNull() {
            addCriterion("pagetitle is null");
            return (Criteria) this;
        }

        public Criteria andPagetitleIsNotNull() {
            addCriterion("pagetitle is not null");
            return (Criteria) this;
        }

        public Criteria andPagetitleEqualTo(String value) {
            addCriterion("pagetitle =", value, "pagetitle");
            return (Criteria) this;
        }

        public Criteria andPagetitleNotEqualTo(String value) {
            addCriterion("pagetitle <>", value, "pagetitle");
            return (Criteria) this;
        }

        public Criteria andPagetitleGreaterThan(String value) {
            addCriterion("pagetitle >", value, "pagetitle");
            return (Criteria) this;
        }

        public Criteria andPagetitleGreaterThanOrEqualTo(String value) {
            addCriterion("pagetitle >=", value, "pagetitle");
            return (Criteria) this;
        }

        public Criteria andPagetitleLessThan(String value) {
            addCriterion("pagetitle <", value, "pagetitle");
            return (Criteria) this;
        }

        public Criteria andPagetitleLessThanOrEqualTo(String value) {
            addCriterion("pagetitle <=", value, "pagetitle");
            return (Criteria) this;
        }

        public Criteria andPagetitleLike(String value) {
            addCriterion("pagetitle like", value, "pagetitle");
            return (Criteria) this;
        }

        public Criteria andPagetitleNotLike(String value) {
            addCriterion("pagetitle not like", value, "pagetitle");
            return (Criteria) this;
        }

        public Criteria andPagetitleIn(List<String> values) {
            addCriterion("pagetitle in", values, "pagetitle");
            return (Criteria) this;
        }

        public Criteria andPagetitleNotIn(List<String> values) {
            addCriterion("pagetitle not in", values, "pagetitle");
            return (Criteria) this;
        }

        public Criteria andPagetitleBetween(String value1, String value2) {
            addCriterion("pagetitle between", value1, value2, "pagetitle");
            return (Criteria) this;
        }

        public Criteria andPagetitleNotBetween(String value1, String value2) {
            addCriterion("pagetitle not between", value1, value2, "pagetitle");
            return (Criteria) this;
        }

        public Criteria andPagesortidIsNull() {
            addCriterion("pagesortid is null");
            return (Criteria) this;
        }

        public Criteria andPagesortidIsNotNull() {
            addCriterion("pagesortid is not null");
            return (Criteria) this;
        }

        public Criteria andPagesortidEqualTo(Integer value) {
            addCriterion("pagesortid =", value, "pagesortid");
            return (Criteria) this;
        }

        public Criteria andPagesortidNotEqualTo(Integer value) {
            addCriterion("pagesortid <>", value, "pagesortid");
            return (Criteria) this;
        }

        public Criteria andPagesortidGreaterThan(Integer value) {
            addCriterion("pagesortid >", value, "pagesortid");
            return (Criteria) this;
        }

        public Criteria andPagesortidGreaterThanOrEqualTo(Integer value) {
            addCriterion("pagesortid >=", value, "pagesortid");
            return (Criteria) this;
        }

        public Criteria andPagesortidLessThan(Integer value) {
            addCriterion("pagesortid <", value, "pagesortid");
            return (Criteria) this;
        }

        public Criteria andPagesortidLessThanOrEqualTo(Integer value) {
            addCriterion("pagesortid <=", value, "pagesortid");
            return (Criteria) this;
        }

        public Criteria andPagesortidIn(List<Integer> values) {
            addCriterion("pagesortid in", values, "pagesortid");
            return (Criteria) this;
        }

        public Criteria andPagesortidNotIn(List<Integer> values) {
            addCriterion("pagesortid not in", values, "pagesortid");
            return (Criteria) this;
        }

        public Criteria andPagesortidBetween(Integer value1, Integer value2) {
            addCriterion("pagesortid between", value1, value2, "pagesortid");
            return (Criteria) this;
        }

        public Criteria andPagesortidNotBetween(Integer value1, Integer value2) {
            addCriterion("pagesortid not between", value1, value2, "pagesortid");
            return (Criteria) this;
        }

        public Criteria andPageauthornameIsNull() {
            addCriterion("pageauthorname is null");
            return (Criteria) this;
        }

        public Criteria andPageauthornameIsNotNull() {
            addCriterion("pageauthorname is not null");
            return (Criteria) this;
        }

        public Criteria andPageauthornameEqualTo(String value) {
            addCriterion("pageauthorname =", value, "pageauthorname");
            return (Criteria) this;
        }

        public Criteria andPageauthornameNotEqualTo(String value) {
            addCriterion("pageauthorname <>", value, "pageauthorname");
            return (Criteria) this;
        }

        public Criteria andPageauthornameGreaterThan(String value) {
            addCriterion("pageauthorname >", value, "pageauthorname");
            return (Criteria) this;
        }

        public Criteria andPageauthornameGreaterThanOrEqualTo(String value) {
            addCriterion("pageauthorname >=", value, "pageauthorname");
            return (Criteria) this;
        }

        public Criteria andPageauthornameLessThan(String value) {
            addCriterion("pageauthorname <", value, "pageauthorname");
            return (Criteria) this;
        }

        public Criteria andPageauthornameLessThanOrEqualTo(String value) {
            addCriterion("pageauthorname <=", value, "pageauthorname");
            return (Criteria) this;
        }

        public Criteria andPageauthornameLike(String value) {
            addCriterion("pageauthorname like", value, "pageauthorname");
            return (Criteria) this;
        }

        public Criteria andPageauthornameNotLike(String value) {
            addCriterion("pageauthorname not like", value, "pageauthorname");
            return (Criteria) this;
        }

        public Criteria andPageauthornameIn(List<String> values) {
            addCriterion("pageauthorname in", values, "pageauthorname");
            return (Criteria) this;
        }

        public Criteria andPageauthornameNotIn(List<String> values) {
            addCriterion("pageauthorname not in", values, "pageauthorname");
            return (Criteria) this;
        }

        public Criteria andPageauthornameBetween(String value1, String value2) {
            addCriterion("pageauthorname between", value1, value2, "pageauthorname");
            return (Criteria) this;
        }

        public Criteria andPageauthornameNotBetween(String value1, String value2) {
            addCriterion("pageauthorname not between", value1, value2, "pageauthorname");
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