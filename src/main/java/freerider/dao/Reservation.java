package freerider.dao;

import java.time.LocalDateTime;

/**
 * DAO type for table RESERVATION:
 * +-------+------+------+------------------+------------------+-----------------+----------------+------------+
 * | ID    |CUS_ID|VEH_ID| BEGIN            | END              | PICKUP          | DROP-OFF       | STATUS     |
 * +-------+------+------+------------------+------------------+-----------------+----------------+------------+
 * | 145373|  1001|  8002| 2025-07-04 20:00:| 2025-07-04 23:00:| Berlin Wedding  | Hamburg        | Inquired   |
 * | 201235|  1000|  8002| 2025-07-20 10:00:| 2025-07-20 20:00:| Berlin Wedding  | Berlin Wedding | Booked     |
 * | 351682|  1002|  8001| 2025-06-11 08:00:| 2025-06-11 20:00:| Berlin Wedding  | Hamburg        | Inquired   |
 * | 382565|  1000|  8006| 2025-07-18 18:00:| 2025-07-07 18:10:| Berlin Wedding  | Hamburg        | Inquired   |
 * | 682351|  1002|  8003| 2025-07-18 09:00:| 2025-07-07 18:00:| Potsdam         | Teltow         | Inquired   |
 * +-------+------+------+------------------+------------------+-----------------+----------------+------------+
 */
public record Reservation(
    int id,             // PRIMARY KEY (ID)
    int customer_id,    // FOREIGN KEY (CUSTOMER_ID) REFERENCES CUSTOMER(ID)
    int vehicle_id,     // FOREIGN KEY (VEHICLE_ID) REFERENCES VEHICLE(ID)
    LocalDateTime begin,
    LocalDateTime end,
    String pickup,
    String dropoff,
    Status status
) {
    enum Status {Inquired, InquiryConfirmed, Booked, Cancelled};
}