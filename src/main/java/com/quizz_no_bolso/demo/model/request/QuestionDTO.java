package com.quizz_no_bolso.demo.model.request;

import java.util.List;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class QuestionDTO {
    @NotNull(message = "Text cannot be null")
    @Size(min = 1, message = "Text must not be empty")
    private String text;
    @NotNull(message = "Options cannot be null")
    @Size(min = 2, message = "At least two options are required")
    @Size(max = 5, message = "A maximum of five options is allowed")
    private List<String> options;
    @NotNull(message = "Correct option index cannot be null")
    @Min(value = 0, message = "Correct option index must be at least 0")
    @Max(value = 4, message = "Correct option index must be at most 4")
    private byte correctOptionIndex;
    @NotNull(message = "Point value cannot be null")
    private int point;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public byte getCorrectOptionIndex() {
        return correctOptionIndex;
    }

    public void setCorrectOptionIndex(byte correctOptionIndex) {
        this.correctOptionIndex = correctOptionIndex;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

}
