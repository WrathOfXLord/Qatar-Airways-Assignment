Getting Started with Gauge
==========================

This is an executable specification file. This file follows markdown syntax. Every heading in this file denotes a scenario. Every bulleted point denotes a step.
To execute this specification, use `mvn test`

Check the application is running
-------------------------------
* Wait for "4" seconds
* Reject offer
* Wait for "4" seconds
* Check if the application is running
* Wait for "4" seconds
* Click the element with "Book" accessibility id
* Wait for "4" seconds
* Click the element with "One-way" accessibility id
* Wait for "4" seconds
* Click the element with "com.m.qr:id/rvmp_fragment_rtow_flight_selection_origin_holder" id
* Wait for "3" seconds
* Send "Paris" text to the element with "com.m.qr:id/rvmp_fragment_ond_selection_filter_edittext" id
* Wait for "3" seconds
* Select airport "2" from the list
* Wait for "3" seconds
* Click the element with "com.m.qr:id/rvmp_fragment_rtow_flight_selection_destination_holder" id
* Wait for "3" seconds
* Send "Qatar" text to the element with "com.m.qr:id/rvmp_fragment_ond_selection_filter_edittext" id
* Wait for "3" seconds
* Select airport "1" from the list
* Wait for "4" seconds
* Click the element with "com.m.qr:id/rvmp_fragment_rtow_flight_selection_date_holder_date_text_view" id
* Wait for "4" seconds
* Select a date
* Wait for "4" seconds
* Click the element with "com.m.qr:id/fragment_calendar_confirm_button" id
* Wait for "4" seconds
* Click the element with "com.m.qr:id/fragment_rtow_flight_cabin_class_text_view" id
* Wait for "4" seconds
* Click the element with "com.m.qr:id/cabin_selection_economy_row" id
* Wait for "4" seconds
* Click the element with "com.m.qr:id/rvmp_booking_search_flights_button" id
* Wait for "4" seconds
* Verify flight selection screen
* Wait for "4" seconds
* Select random flight
* Wait for "4" seconds
* Click the element with "com.m.qr:id/rvmp_activity_flight_details_select_button" id
* Wait for "4" seconds
* Check if the times are equal
