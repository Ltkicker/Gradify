package com.github.ltkicker.gradify.data.grades;

public class parent_category {

    //declaration of variables//
        private String sub_category; //category sa parent percentage mag consist ug 1-4 or 5
        private int raw_score; //raw scores para sa mga students
        private double parent_category_percentage; //mao ni ang pilay i set sa user nga percentage

        public parent_category(String sub_category, int raw_score,double parent_category_percentage) {
            this.sub_category = sub_category;
            this.raw_score = raw_score;
            this.parent_category_percentage=parent_category_percentage;
        }

         public double getParent_category_percentage() {
                  return parent_category_percentage;
         }

         public void setParent_category_percentage(double parent_category_percentage) {
                this.parent_category_percentage = parent_category_percentage;
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
