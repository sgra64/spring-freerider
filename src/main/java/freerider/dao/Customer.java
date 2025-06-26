package freerider.dao;

/**
 * DAO type for table CUSTOMER:
 * +------+---------------------+---------------------+---------------+
 * | ID   | NAME                | CONTACT             | STATUS        |
 * +------+---------------------+---------------------+---------------+
 * | 1000 | Meyer, Eric         | eme22@gmail.com     | Active        |
 * | 1001 | Sommer, Tina        | 030 22458 29425     | Active        |
 * | 1002 | Schulze, Tim        | +49 171 2358124     | InRegistratio |
 * +------+---------------------+---------------------+---------------+
 */
public record Customer(
    int id,         // PRIMARY KEY (ID)
    String name,
    String contact,
    Status status
) {
    public enum Status {Active, InRegistration, Terminated};
}