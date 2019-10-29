package com.homeedition.reservation.exceptions;

public class ReservationException extends RuntimeException {

    ReservationException(String message) {
        super(message);
    }

    public static class AlreadyReserved extends ReservationException {

        public AlreadyReserved(String message) {
            super(message);
        }
    }

    public static class DateOrTimeIncorrect extends ReservationException {

        public DateOrTimeIncorrect(String message) {
            super(message);
        }
    }


}
