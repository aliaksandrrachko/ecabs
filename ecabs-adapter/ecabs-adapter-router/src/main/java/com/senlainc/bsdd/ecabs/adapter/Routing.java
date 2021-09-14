package com.senlainc.bsdd.ecabs.adapter;

/**
 * Class containing routes
 */
public final class Routing {
    private Routing(){}

    private static final String BOOKING_ROUTES_KEY = "ecabs.booking";

    private static final String E_CABS_PREFIX = "ecabs";

    public static final class MessageExchange {
        private MessageExchange(){}

        public static final String E_CABS_MESSAGE_EXCHANGE = E_CABS_PREFIX + "-message-exchange";

        public static final String E_CABS_MESSAGE_AUDIT_QUEUE = E_CABS_PREFIX + "-message-audit-queue";

        public static final String BOOKING_AUDIT_ROUTES_KEY = BOOKING_ROUTES_KEY + ".#";

        public static final class BookingExchange {
            private BookingExchange() {}

            public static final String E_CABS_BOOKING_EXCHANGE = E_CABS_PREFIX + "-booking-exchange";

            public static final String E_CABS_BOOKING_ADD_QUEUE = "ecabs-booking-add-queue";
            public static final String E_CABS_BOOKING_EDIT_QUEUE = "ecabs-booking-edit-queue";
            public static final String E_CABS_BOOKING_DEL_QUEUE = "ecabs-booking-delete-queue";

            public static final String BOOKING_PUT_ROUTES_KEY = BOOKING_ROUTES_KEY + ".put";
            public static final String BOOKING_ADD_ROUTES_KEY = BOOKING_ROUTES_KEY + ".add";
            public static final String BOOKING_DELETE_ROUTES_KEY = BOOKING_ROUTES_KEY + ".del";
        }
    }
}
