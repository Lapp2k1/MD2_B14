package BT2;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;


class CurrentDate {
    public static void main(String[] args) {
        LocalDate currentDate = LocalDate.now();
        System.out.println("Ngày hiện tại của hệ thống: " + currentDate);

        LocalDate tokyoDate = LocalDate.now(ZoneId.of("Asia/Tokyo"));
        System.out.println("Ngày hiện tại ở Tokyo: " + tokyoDate);

        LocalDate sydneyDate = LocalDate.now(ZoneId.of("Australia/Sydney"));
        System.out.println("Ngày hiện tại ở Sydney: " + sydneyDate);

        LocalDate saoPauloDate = LocalDate.now(ZoneId.of("America/Sao_Paulo"));
        System.out.println("Ngày hiện tại ở Sao Paulo: " + saoPauloDate);
    }
}


class CurrentTime {
    public static void main(String[] args) {
        LocalTime currentTime = LocalTime.now();
        System.out.println("Thời gian hiện tại: " + currentTime);
    }
}


class DaysInCurrentMonth {
    public static void main(String[] args) {
        YearMonth yearMonth = YearMonth.now();
        int daysInMonth = yearMonth.lengthOfMonth();
        System.out.println("Số ngày trong tháng hiện tại: " + daysInMonth);
    }
}

class DaysInCurrentYear {
    public static void main(String[] args) {
        Year currentYear = Year.now();
        int daysInYear = currentYear.isLeap() ? 366 : 365;
        System.out.println("Số ngày trong năm hiện tại: " + daysInYear);
    }
}


class StringToDate {
    public static void main(String[] args) {
        String dateString = "2024-08-27";
        LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.println("Chuỗi ngày đã chuyển đổi: " + date);
    }
}


class DateToString {
    public static void main(String[] args) {
        LocalDate date = LocalDate.now();
        String formattedDate = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        System.out.println("Chuỗi ngày đã định dạng: " + formattedDate);
    }
}


class DateTimeToString {
    public static void main(String[] args) {
        LocalDateTime dateTime = LocalDateTime.now();
        String formattedDateTime = dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        System.out.println("Chuỗi ngày và giờ đã định dạng: " + formattedDateTime);
    }
}


class NextPreviousDay {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now();

        LocalDate nextDay = today.plusDays(1);
        System.out.println("Ngày tiếp theo: " + nextDay);

        LocalDate previousDay = today.minusDays(1);
        System.out.println("Ngày trước đó: " + previousDay);
    }
}


