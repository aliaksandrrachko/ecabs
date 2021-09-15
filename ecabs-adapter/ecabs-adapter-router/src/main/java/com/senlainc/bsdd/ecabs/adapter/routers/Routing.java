package com.senlainc.bsdd.ecabs.adapter.routers;

/**
 * Class containing queues names, exchanges names and routing's keys.
 */
public final class Routing {
    private Routing(){}

    private static final String BOOKING_ROUTES_KEY = "ecabs.booking";

    private static final String E_CABS_PREFIX = "ecabs";

    public static final class MESSAGE_EXCHANGE {
        private MESSAGE_EXCHANGE(){}

        public static final String E_CABS_MESSAGE_EXCHANGE = E_CABS_PREFIX + "-message-exchange";

        public static final String E_CABS_MESSAGE_AUDIT_QUEUE = E_CABS_PREFIX + "-message-audit-queue";

        public static final String E_CABS_MESSAGE_AUDIT_ROUTES_KEY = BOOKING_ROUTES_KEY + ".#";

        public static final class BOOKING_EXCHANGE {
            private BOOKING_EXCHANGE() {}

            public static final String E_CABS_BOOKING_EXCHANGE = E_CABS_PREFIX + "-booking-exchange";

            public static final String E_CABS_BOOKING_ADD_QUEUE = "ecabs-booking-add-queue";
            public static final String E_CABS_BOOKING_EDIT_QUEUE = "ecabs-booking-edit-queue";
            public static final String E_CABS_BOOKING_DEL_QUEUE = "ecabs-booking-delete-queue";

            public static final String E_CABS_BOOKING_ADD_ROUTES_KEY = BOOKING_ROUTES_KEY + ".add";
            public static final String E_CABS_BOOKING_EDIT_ROUTES_KEY = BOOKING_ROUTES_KEY + ".put";
            public static final String E_CABS_BOOKING_DEL_ROUTES_KEY = BOOKING_ROUTES_KEY + ".del";
        }
    }
}
