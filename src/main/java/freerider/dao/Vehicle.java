package freerider.dao;

/**
 * DAO type for table VEHICLE:
 * +------+---------------------+---------------------+-----+---------+----------+------------+
 * | ID   | MAKE                | MODEL               | SEA | CATEG   | POWER    | STATUS     |
 * +------+---------------------+---------------------+-----+---------+----------+------------+
 * | 8000 | VW                  | ID.3                |   4 | Sedan   | Electric | Active     |
 * | 8001 | VW                  | Golf                |   4 | Sedan   | Gasoline | Active     |
 * | 8002 | VW                  | Golf                |   4 | Sedan   | Hybrid   | Active     |
 * | 8003 | BMW                 | 320d                |   4 | Sedan   | Diesel   | Active     |
 * | 8004 | Mercedes            | EQS                 |   4 | Sedan   | Electric | Active     |
 * | 8005 | VW                  | Multivan Life       |   8 | Van     | Gasoline | Active     |
 * | 8006 | Tesla               | Model 3             |   4 | Sedan   | Electric | Active     |
 * | 8007 | Tesla               | Model S             |   4 | Sedan   | Electric | Serviced   |
 * +------+---------------------+---------------------+-----+---------+----------+------------+
 */
public record Vehicle(
    int id,         // PRIMARY KEY (ID)
    String make,
    String model,
    int seats,
    Category category,
    Power power,
    Status status
) {
    enum Category {Sedan, SUV, Convertible, Van, Bike};
    enum Power {Gasoline, Diesel, Electric, Hybrid, Hydrogen};
    enum Status {Active, Serviced, Terminated};
}