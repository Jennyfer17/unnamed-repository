package com.quizz_no_bolso.demo.model.request;

import jakarta.validation.constraints.NotNull;

public class RequestSubmitAnswer {
    @NotNull(message = "Option cannot be null")
    private byte option;

    public byte getOption() {
        return option;
    }

    public void setOption(byte option) {
        this.option = option;
    }
}
