package org.example;

public class Student {
    private String gender;
    private String raceEthnicity;
    private String parentalLevelOfEducation;
    private String lunch;
    private String testPreparationCourse;
    private int mathScore;
    private int readingScore;
    private int writingScore;

    public Student(String gender,
                   String raceEthnicity,
                   String parentalLevelOfEducation,
                   String lunch,
                   String testPreparationCourse,
                   int mathScore,
                   int readingScore,
                   int writingScore) {
        this.gender = gender;
        this.raceEthnicity = raceEthnicity;
        this.parentalLevelOfEducation = parentalLevelOfEducation;
        this.lunch = lunch;
        this.testPreparationCourse = testPreparationCourse;
        this.mathScore = mathScore;
        this.readingScore = readingScore;
        this.writingScore = writingScore;
    }

    public String getGender() {
        return gender;
    }

    public String getRaceEthnicity() {
        return raceEthnicity;
    }

    public String getParentalLevelOfEducation() {
        return parentalLevelOfEducation;
    }

    public String getLunch() {
        return lunch;
    }

    public String getTestPreparationCourse() {
        return testPreparationCourse;
    }

    public int getMathScore() {
        return mathScore;
    }

    public int getReadingScore() {
        return readingScore;
    }

    public int getWritingScore() {
        return writingScore;
    }
}