package com.github.ltkicker.gradify.data.grades;

public class parent_category {

    //declaration of variables
        private String sub_category;
        private int raw_score;
        private double parent_category_pertage;

        public parent_category(String sub_category, int raw_score,double parent_category_pertage) {
            this.sub_category = sub_category;
            this.raw_score = raw_score;
            this.parent_category_pertage=parent_category_pertage;
        }

         public double getParent_category_pertage() {
                  return parent_category_pertage;
         }

         public void setParent_category_pertage(double parent_category_pertage) {
                this.parent_category_pertage = parent_category_pertage;
            }

        public String getSub_category() {
            return sub_category;
        }
          public void setSub_category(String sub_category) {
          this.sub_category = sub_category;
         }

        public int getRaw_score() {
            return raw_score;
        }

        public void setRaw_score(int raw_score) {
            this.raw_score = raw_score;
        }


    }
