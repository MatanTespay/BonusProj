/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

/**
 *
 * @author asus
 */
public class Column {

        private String name;
        private String sqlColumnName;
        private Class cClass;

        public Column(String name, String sqlName, Class cClass) {
            this.name = name;
            this.sqlColumnName = sqlName;
            this.cClass = cClass;
        }

        /**
         * @return the name
         */
        public String getName() {
            return name;
        }

        /**
         * @param name the name to set
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * @return the sqlColumnName
         */
        public String getSqlName() {
            return getSqlColumnName();
        }

        /**
         * @param sqlName the sqlColumnName to set
         */
        public void setSqlName(String sqlName) {
            this.sqlColumnName = sqlName;
        }

        /**
         * @return the cClass
         */
        public Class getcClass() {
            return cClass;
        }

        /**
         * @param cClass the cClass to set
         */
        public void setcClass(Class cClass) {
            this.cClass = cClass;
        }

        /**
         * @return the sqlColumnName
         */
        public String getSqlColumnName() {
            return sqlColumnName;
        }
    }
