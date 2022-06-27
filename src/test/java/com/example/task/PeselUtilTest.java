package com.example.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class PeselUtilTest {

    @Test
    public void shouldReturnBirthdayInDecemberOf2000Century() {

        LocalDate result = PeselUtil.getBirthday("01321344343");

        assertThat(result).isEqualTo("2001-12-13");
    }

    @Test
    public void shouldBirthdayInFebruaryOf2000Century() {

        LocalDate result = PeselUtil.getBirthday("01221344343");

        assertThat(result).isEqualTo("2001-02-13");
    }

    @Test
    public void shouldReturnBirthdayInNovemberOf1800Century() {

        LocalDate result = PeselUtil.getBirthday("29912944343");

        assertThat(result).isEqualTo("1829-11-29");
    }

    @Test
    public void shouldReturnBirthdayInJuneOf1800Century() {

        LocalDate result = PeselUtil.getBirthday("99860344343");

        assertThat(result).isEqualTo("1899-06-03");
    }

    @Test
    public void shouldReturnBirthdayInMayOf1900Century() {

        LocalDate result = PeselUtil.getBirthday("99052144343");

        assertThat(result).isEqualTo("1999-05-21");
    }

    @Test
    public void shouldReturnBirthdayInJulyOf2100Century() {

        LocalDate result = PeselUtil.getBirthday("92472044343");

        assertThat(result).isEqualTo("2192-07-20");
    }

    @Test
    public void shouldReturnBirthdayInOctoberOf2100Century() {

        LocalDate result = PeselUtil.getBirthday("92501044343");

        assertThat(result).isEqualTo("2192-10-10");
    }

    @Test
    public void shouldReturnBirthdayInJanuaryOf2200Century() {

        LocalDate result = PeselUtil.getBirthday("55611044343");

        assertThat(result).isEqualTo("2255-01-10");
    }

    @Test
    public void shouldReturnBirthdayInNovemberOf2200Century() {

        LocalDate result = PeselUtil.getBirthday("42710944343");

        assertThat(result).isEqualTo("2242-11-09");
    }

}