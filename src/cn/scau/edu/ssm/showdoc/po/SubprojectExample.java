package cn.scau.edu.ssm.showdoc.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class SubprojectExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SubprojectExample() {
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

        public Criteria andSubprojectidIsNull() {
            addCriterion("subprojectid is null");
            return (Criteria) this;
        }

        public Criteria andSubprojectidIsNotNull() {
            addCriterion("subprojectid is not null");
            return (Criteria) this;
        }

        public Criteria andSubprojectidEqualTo(Integer value) {
            addCriterion("subprojectid =", value, "subprojectid");
            return (Criteria) this;
        }

        public Criteria andSubprojectidNotEqualTo(Integer value) {
            addCriterion("subprojectid <>", value, "subprojectid");
            return (Criteria) this;
        }

        public Criteria andSubprojectidGreaterThan(Integer value) {
            addCriterion("subprojectid >", value, "subprojectid");
            return (Criteria) this;
        }

        public Criteria andSubprojectidGreaterThanOrEqualTo(Integer value) {
            addCriterion("subprojectid >=", value, "subprojectid");
            return (Criteria) this;
        }

        public Criteria andSubprojectidLessThan(Integer value) {
            addCriterion("subprojectid <", value, "subprojectid");
            return (Criteria) this;
        }

        public Criteria andSubprojectidLessThanOrEqualTo(Integer value) {
            addCriterion("subprojectid <=", value, "subprojectid");
            return (Criteria) this;
        }

        public Criteria andSubprojectidIn(List<Integer> values) {
            addCriterion("subprojectid in", values, "subprojectid");
            return (Criteria) this;
        }

        public Criteria andSubprojectidNotIn(List<Integer> values) {
            addCriterion("subprojectid not in", values, "subprojectid");
            return (Criteria) this;
        }

        public Criteria andSubprojectidBetween(Integer value1, Integer value2) {
            addCriterion("subprojectid between", value1, value2, "subprojectid");
            return (Criteria) this;
        }

        public Criteria andSubprojectidNotBetween(Integer value1, Integer value2) {
            addCriterion("subprojectid not between", value1, value2, "subprojectid");
            return (Criteria) this;
        }

        public Criteria andSubprojectnameIsNull() {
            addCriterion("subprojectname is null");
            return (Criteria) this;
        }

        public Criteria andSubprojectnameIsNotNull() {
            addCriterion("subprojectname is not null");
            return (Criteria) this;
        }

        public Criteria andSubprojectnameEqualTo(String value) {
            addCriterion("subprojectname =", value, "subprojectname");
            return (Criteria) this;
        }

        public Criteria andSubprojectnameNotEqualTo(String value) {
            addCriterion("subprojectname <>", value, "subprojectname");
            return (Criteria) this;
        }

        public Criteria andSubprojectnameGreaterThan(String value) {
            addCriterion("subprojectname >", value, "subprojectname");
            return (Criteria) this;
        }

        public Criteria andSubprojectnameGreaterThanOrEqualTo(String value) {
            addCriterion("subprojectname >=", value, "subprojectname");
            return (Criteria) this;
        }

        public Criteria andSubprojectnameLessThan(String value) {
            addCriterion("subprojectname <", value, "subprojectname");
            return (Criteria) this;
        }

        public Criteria andSubprojectnameLessThanOrEqualTo(String value) {
            addCriterion("subprojectname <=", value, "subprojectname");
            return (Criteria) this;
        }

        public Criteria andSubprojectnameLike(String value) {
            addCriterion("subprojectname like", value, "subprojectname");
            return (Criteria) this;
        }

        public Criteria andSubprojectnameNotLike(String value) {
            addCriterion("subprojectname not like", value, "subprojectname");
            return (Criteria) this;
        }

        public Criteria andSubprojectnameIn(List<String> values) {
            addCriterion("subprojectname in", values, "subprojectname");
            return (Criteria) this;
        }

        public Criteria andSubprojectnameNotIn(List<String> values) {
            addCriterion("subprojectname not in", values, "subprojectname");
            return (Criteria) this;
        }

        public Criteria andSubprojectnameBetween(String value1, String value2) {
            addCriterion("subprojectname between", value1, value2, "subprojectname");
            return (Criteria) this;
        }

        public Criteria andSubprojectnameNotBetween(String value1, String value2) {
            addCriterion("subprojectname not between", value1, value2, "subprojectname");
            return (Criteria) this;
        }

        public Criteria andProjectidIsNull() {
            addCriterion("projectid is null");
            return (Criteria) this;
        }

        public Criteria andProjectidIsNotNull() {
            addCriterion("projectid is not null");
            return (Criteria) this;
        }

        public Criteria andProjectidEqualTo(Integer value) {
            addCriterion("projectid =", value, "projectid");
            return (Criteria) this;
        }

        public Criteria andProjectidNotEqualTo(Integer value) {
            addCriterion("projectid <>", value, "projectid");
            return (Criteria) this;
        }

        public Criteria andProjectidGreaterThan(Integer value) {
            addCriterion("projectid >", value, "projectid");
            return (Criteria) this;
        }

        public Criteria andProjectidGreaterThanOrEqualTo(Integer value) {
            addCriterion("projectid >=", value, "projectid");
            return (Criteria) this;
        }

        public Criteria andProjectidLessThan(Integer value) {
            addCriterion("projectid <", value, "projectid");
            return (Criteria) this;
        }

        public Criteria andProjectidLessThanOrEqualTo(Integer value) {
            addCriterion("projectid <=", value, "projectid");
            return (Criteria) this;
        }

        public Criteria andProjectidIn(List<Integer> values) {
            addCriterion("projectid in", values, "projectid");
            return (Criteria) this;
        }

        public Criteria andProjectidNotIn(List<Integer> values) {
            addCriterion("projectid not in", values, "projectid");
            return (Criteria) this;
        }

        public Criteria andProjectidBetween(Integer value1, Integer value2) {
            addCriterion("projectid between", value1, value2, "projectid");
            return (Criteria) this;
        }

        public Criteria andProjectidNotBetween(Integer value1, Integer value2) {
            addCriterion("projectid not between", value1, value2, "projectid");
            return (Criteria) this;
        }

        public Criteria andSublevelIsNull() {
            addCriterion("sublevel is null");
            return (Criteria) this;
        }

        public Criteria andSublevelIsNotNull() {
            addCriterion("sublevel is not null");
            return (Criteria) this;
        }

        public Criteria andSublevelEqualTo(Integer value) {
            addCriterion("sublevel =", value, "sublevel");
            return (Criteria) this;
        }

        public Criteria andSublevelNotEqualTo(Integer value) {
            addCriterion("sublevel <>", value, "sublevel");
            return (Criteria) this;
        }

        public Criteria andSublevelGreaterThan(Integer value) {
            addCriterion("sublevel >", value, "sublevel");
            return (Criteria) this;
        }

        public Criteria andSublevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("sublevel >=", value, "sublevel");
            return (Criteria) this;
        }

        public Criteria andSublevelLessThan(Integer value) {
            addCriterion("sublevel <", value, "sublevel");
            return (Criteria) this;
        }

        public Criteria andSublevelLessThanOrEqualTo(Integer value) {
            addCriterion("sublevel <=", value, "sublevel");
            return (Criteria) this;
        }

        public Criteria andSublevelIn(List<Integer> values) {
            addCriterion("sublevel in", values, "sublevel");
            return (Criteria) this;
        }

        public Criteria andSublevelNotIn(List<Integer> values) {
            addCriterion("sublevel not in", values, "sublevel");
            return (Criteria) this;
        }

        public Criteria andSublevelBetween(Integer value1, Integer value2) {
            addCriterion("sublevel between", value1, value2, "sublevel");
            return (Criteria) this;
        }

        public Criteria andSublevelNotBetween(Integer value1, Integer value2) {
            addCriterion("sublevel not between", value1, value2, "sublevel");
            return (Criteria) this;
        }

        public Criteria andParentidIsNull() {
            addCriterion("parentid is null");
            return (Criteria) this;
        }

        public Criteria andParentidIsNotNull() {
            addCriterion("parentid is not null");
            return (Criteria) this;
        }

        public Criteria andParentidEqualTo(Integer value) {
            addCriterion("parentid =", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidNotEqualTo(Integer value) {
            addCriterion("parentid <>", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidGreaterThan(Integer value) {
            addCriterion("parentid >", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidGreaterThanOrEqualTo(Integer value) {
            addCriterion("parentid >=", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidLessThan(Integer value) {
            addCriterion("parentid <", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidLessThanOrEqualTo(Integer value) {
            addCriterion("parentid <=", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidIn(List<Integer> values) {
            addCriterion("parentid in", values, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidNotIn(List<Integer> values) {
            addCriterion("parentid not in", values, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidBetween(Integer value1, Integer value2) {
            addCriterion("parentid between", value1, value2, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidNotBetween(Integer value1, Integer value2) {
            addCriterion("parentid not between", value1, value2, "parentid");
            return (Criteria) this;
        }

        public Criteria andSubsortidIsNull() {
            addCriterion("subsortid is null");
            return (Criteria) this;
        }

        public Criteria andSubsortidIsNotNull() {
            addCriterion("subsortid is not null");
            return (Criteria) this;
        }

        public Criteria andSubsortidEqualTo(Integer value) {
            addCriterion("subsortid =", value, "subsortid");
            return (Criteria) this;
        }

        public Criteria andSubsortidNotEqualTo(Integer value) {
            addCriterion("subsortid <>", value, "subsortid");
            return (Criteria) this;
        }

        public Criteria andSubsortidGreaterThan(Integer value) {
            addCriterion("subsortid >", value, "subsortid");
            return (Criteria) this;
        }

        public Criteria andSubsortidGreaterThanOrEqualTo(Integer value) {
            addCriterion("subsortid >=", value, "subsortid");
            return (Criteria) this;
        }

        public Criteria andSubsortidLessThan(Integer value) {
            addCriterion("subsortid <", value, "subsortid");
            return (Criteria) this;
        }

        public Criteria andSubsortidLessThanOrEqualTo(Integer value) {
            addCriterion("subsortid <=", value, "subsortid");
            return (Criteria) this;
        }

        public Criteria andSubsortidIn(List<Integer> values) {
            addCriterion("subsortid in", values, "subsortid");
            return (Criteria) this;
        }

        public Criteria andSubsortidNotIn(List<Integer> values) {
            addCriterion("subsortid not in", values, "subsortid");
            return (Criteria) this;
        }

        public Criteria andSubsortidBetween(Integer value1, Integer value2) {
            addCriterion("subsortid between", value1, value2, "subsortid");
            return (Criteria) this;
        }

        public Criteria andSubsortidNotBetween(Integer value1, Integer value2) {
            addCriterion("subsortid not between", value1, value2, "subsortid");
            return (Criteria) this;
        }

        public Criteria andSubpstatuIsNull() {
            addCriterion("subpstatu is null");
            return (Criteria) this;
        }

        public Criteria andSubpstatuIsNotNull() {
            addCriterion("subpstatu is not null");
            return (Criteria) this;
        }

        public Criteria andSubpstatuEqualTo(Integer value) {
            addCriterion("subpstatu =", value, "subpstatu");
            return (Criteria) this;
        }

        public Criteria andSubpstatuNotEqualTo(Integer value) {
            addCriterion("subpstatu <>", value, "subpstatu");
            return (Criteria) this;
        }

        public Criteria andSubpstatuGreaterThan(Integer value) {
            addCriterion("subpstatu >", value, "subpstatu");
            return (Criteria) this;
        }

        public Criteria andSubpstatuGreaterThanOrEqualTo(Integer value) {
            addCriterion("subpstatu >=", value, "subpstatu");
            return (Criteria) this;
        }

        public Criteria andSubpstatuLessThan(Integer value) {
            addCriterion("subpstatu <", value, "subpstatu");
            return (Criteria) this;
        }

        public Criteria andSubpstatuLessThanOrEqualTo(Integer value) {
            addCriterion("subpstatu <=", value, "subpstatu");
            return (Criteria) this;
        }

        public Criteria andSubpstatuIn(List<Integer> values) {
            addCriterion("subpstatu in", values, "subpstatu");
            return (Criteria) this;
        }

        public Criteria andSubpstatuNotIn(List<Integer> values) {
            addCriterion("subpstatu not in", values, "subpstatu");
            return (Criteria) this;
        }

        public Criteria andSubpstatuBetween(Integer value1, Integer value2) {
            addCriterion("subpstatu between", value1, value2, "subpstatu");
            return (Criteria) this;
        }

        public Criteria andSubpstatuNotBetween(Integer value1, Integer value2) {
            addCriterion("subpstatu not between", value1, value2, "subpstatu");
            return (Criteria) this;
        }

        public Criteria andSubauthornameIsNull() {
            addCriterion("subauthorname is null");
            return (Criteria) this;
        }

        public Criteria andSubauthornameIsNotNull() {
            addCriterion("subauthorname is not null");
            return (Criteria) this;
        }

        public Criteria andSubauthornameEqualTo(String value) {
            addCriterion("subauthorname =", value, "subauthorname");
            return (Criteria) this;
        }

        public Criteria andSubauthornameNotEqualTo(String value) {
            addCriterion("subauthorname <>", value, "subauthorname");
            return (Criteria) this;
        }

        public Criteria andSubauthornameGreaterThan(String value) {
            addCriterion("subauthorname >", value, "subauthorname");
            return (Criteria) this;
        }

        public Criteria andSubauthornameGreaterThanOrEqualTo(String value) {
            addCriterion("subauthorname >=", value, "subauthorname");
            return (Criteria) this;
        }

        public Criteria andSubauthornameLessThan(String value) {
            addCriterion("subauthorname <", value, "subauthorname");
            return (Criteria) this;
        }

        public Criteria andSubauthornameLessThanOrEqualTo(String value) {
            addCriterion("subauthorname <=", value, "subauthorname");
            return (Criteria) this;
        }

        public Criteria andSubauthornameLike(String value) {
            addCriterion("subauthorname like", value, "subauthorname");
            return (Criteria) this;
        }

        public Criteria andSubauthornameNotLike(String value) {
            addCriterion("subauthorname not like", value, "subauthorname");
            return (Criteria) this;
        }

        public Criteria andSubauthornameIn(List<String> values) {
            addCriterion("subauthorname in", values, "subauthorname");
            return (Criteria) this;
        }

        public Criteria andSubauthornameNotIn(List<String> values) {
            addCriterion("subauthorname not in", values, "subauthorname");
            return (Criteria) this;
        }

        public Criteria andSubauthornameBetween(String value1, String value2) {
            addCriterion("subauthorname between", value1, value2, "subauthorname");
            return (Criteria) this;
        }

        public Criteria andSubauthornameNotBetween(String value1, String value2) {
            addCriterion("subauthorname not between", value1, value2, "subauthorname");
            return (Criteria) this;
        }

        public Criteria andSubprojectpasswordIsNull() {
            addCriterion("subprojectpassword is null");
            return (Criteria) this;
        }

        public Criteria andSubprojectpasswordIsNotNull() {
            addCriterion("subprojectpassword is not null");
            return (Criteria) this;
        }

        public Criteria andSubprojectpasswordEqualTo(String value) {
            addCriterion("subprojectpassword =", value, "subprojectpassword");
            return (Criteria) this;
        }

        public Criteria andSubprojectpasswordNotEqualTo(String value) {
            addCriterion("subprojectpassword <>", value, "subprojectpassword");
            return (Criteria) this;
        }

        public Criteria andSubprojectpasswordGreaterThan(String value) {
            addCriterion("subprojectpassword >", value, "subprojectpassword");
            return (Criteria) this;
        }

        public Criteria andSubprojectpasswordGreaterThanOrEqualTo(String value) {
            addCriterion("subprojectpassword >=", value, "subprojectpassword");
            return (Criteria) this;
        }

        public Criteria andSubprojectpasswordLessThan(String value) {
            addCriterion("subprojectpassword <", value, "subprojectpassword");
            return (Criteria) this;
        }

        public Criteria andSubprojectpasswordLessThanOrEqualTo(String value) {
            addCriterion("subprojectpassword <=", value, "subprojectpassword");
            return (Criteria) this;
        }

        public Criteria andSubprojectpasswordLike(String value) {
            addCriterion("subprojectpassword like", value, "subprojectpassword");
            return (Criteria) this;
        }

        public Criteria andSubprojectpasswordNotLike(String value) {
            addCriterion("subprojectpassword not like", value, "subprojectpassword");
            return (Criteria) this;
        }

        public Criteria andSubprojectpasswordIn(List<String> values) {
            addCriterion("subprojectpassword in", values, "subprojectpassword");
            return (Criteria) this;
        }

        public Criteria andSubprojectpasswordNotIn(List<String> values) {
            addCriterion("subprojectpassword not in", values, "subprojectpassword");
            return (Criteria) this;
        }

        public Criteria andSubprojectpasswordBetween(String value1, String value2) {
            addCriterion("subprojectpassword between", value1, value2, "subprojectpassword");
            return (Criteria) this;
        }

        public Criteria andSubprojectpasswordNotBetween(String value1, String value2) {
            addCriterion("subprojectpassword not between", value1, value2, "subprojectpassword");
            return (Criteria) this;
        }

        public Criteria andSubdateIsNull() {
            addCriterion("subdate is null");
            return (Criteria) this;
        }

        public Criteria andSubdateIsNotNull() {
            addCriterion("subdate is not null");
            return (Criteria) this;
        }

        public Criteria andSubdateEqualTo(Date value) {
            addCriterionForJDBCDate("subdate =", value, "subdate");
            return (Criteria) this;
        }

        public Criteria andSubdateNotEqualTo(Date value) {
            addCriterionForJDBCDate("subdate <>", value, "subdate");
            return (Criteria) this;
        }

        public Criteria andSubdateGreaterThan(Date value) {
            addCriterionForJDBCDate("subdate >", value, "subdate");
            return (Criteria) this;
        }

        public Criteria andSubdateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("subdate >=", value, "subdate");
            return (Criteria) this;
        }

        public Criteria andSubdateLessThan(Date value) {
            addCriterionForJDBCDate("subdate <", value, "subdate");
            return (Criteria) this;
        }

        public Criteria andSubdateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("subdate <=", value, "subdate");
            return (Criteria) this;
        }

        public Criteria andSubdateIn(List<Date> values) {
            addCriterionForJDBCDate("subdate in", values, "subdate");
            return (Criteria) this;
        }

        public Criteria andSubdateNotIn(List<Date> values) {
            addCriterionForJDBCDate("subdate not in", values, "subdate");
            return (Criteria) this;
        }

        public Criteria andSubdateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("subdate between", value1, value2, "subdate");
            return (Criteria) this;
        }

        public Criteria andSubdateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("subdate not between", value1, value2, "subdate");
            return (Criteria) this;
        }

        public Criteria andHassubprojectIsNull() {
            addCriterion("hassubproject is null");
            return (Criteria) this;
        }

        public Criteria andHassubprojectIsNotNull() {
            addCriterion("hassubproject is not null");
            return (Criteria) this;
        }

        public Criteria andHassubprojectEqualTo(Integer value) {
            addCriterion("hassubproject =", value, "hassubproject");
            return (Criteria) this;
        }

        public Criteria andHassubprojectNotEqualTo(Integer value) {
            addCriterion("hassubproject <>", value, "hassubproject");
            return (Criteria) this;
        }

        public Criteria andHassubprojectGreaterThan(Integer value) {
            addCriterion("hassubproject >", value, "hassubproject");
            return (Criteria) this;
        }

        public Criteria andHassubprojectGreaterThanOrEqualTo(Integer value) {
            addCriterion("hassubproject >=", value, "hassubproject");
            return (Criteria) this;
        }

        public Criteria andHassubprojectLessThan(Integer value) {
            addCriterion("hassubproject <", value, "hassubproject");
            return (Criteria) this;
        }

        public Criteria andHassubprojectLessThanOrEqualTo(Integer value) {
            addCriterion("hassubproject <=", value, "hassubproject");
            return (Criteria) this;
        }

        public Criteria andHassubprojectIn(List<Integer> values) {
            addCriterion("hassubproject in", values, "hassubproject");
            return (Criteria) this;
        }

        public Criteria andHassubprojectNotIn(List<Integer> values) {
            addCriterion("hassubproject not in", values, "hassubproject");
            return (Criteria) this;
        }

        public Criteria andHassubprojectBetween(Integer value1, Integer value2) {
            addCriterion("hassubproject between", value1, value2, "hassubproject");
            return (Criteria) this;
        }

        public Criteria andHassubprojectNotBetween(Integer value1, Integer value2) {
            addCriterion("hassubproject not between", value1, value2, "hassubproject");
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