# 8. *Data Access Objects (DAO)* and *RowMapper*

Topics:

1. [*Data Access Objects (DAO)*](#1-data-access-objects-dao)

1. [*DAO, BO, DTO Pattern*](#2-dao-bo-dto-pattern)

1. [*RowMapper* Interface](#2-rowmapper-interface)



&nbsp;

## 1. *Data Access Objects (DAO)*


[*`RowMapper<T>`*](https://www.geeksforgeeks.org/java/spring-rowmapper-interface-with-example/)
is a Java interface to map the *rows of a*
[*ResultSet*](https://docs.oracle.com/javase/8/docs/api/java/sql/ResultSet.html)
into
[*Data Access Objects (DAO)*](https://www.oracle.com/java/technologies/dataaccessobject.html)
of type `<T>`.

Rows of a *ResultSet* obtained from a database *SELECT*-query are merely text data:

```
| 1000 | Meyer, Eric         | eme22@gmail.com     | Active        |    <-- row
```

DAO represent rows from tables as objects as they reside in the database.
Therefore, DAO are copies of underlying table data and thus should be
*immutable* (cannot be altered) and not contain logic.

Java `record` should therefore be used for DAO types:

```java
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
    enum Status {Active, InRegistration, Terminated};
}
```

Compare DAO types from package
[*src/main/java/freerider/dao*](src/main/java/freerider/dao) for:

- record [*Customer.java*](src/main/java/freerider/dao/Customer.java),

- record [*Vehicle.java*](src/main/java/freerider/dao/Vehicle.java),

- record [*Reservation.java*](src/main/java/freerider/dao/Reservation.java).



&nbsp;

## 2. *DAO, BO, DTO Pattern*

The
[*DAO Pattern*](https://www.oracle.com/java/technologies/dataaccessobject.html)
relates different types:

- [*Data Access Objects (DAO)*](https://www.oracle.com/java/technologies/dataaccessobject.html)
    wrapping objects (rows) stored in a table in a database (*"datasource"*).

- [*Business Object (BO)*](https://stackoverflow.com/questions/2362380/what-defines-a-business-object)
    the actual business entity to represent.

- [*Data Access Objects (DAO)*](https://www.naukri.com/code360/library/data-transfer-objects-dto-in-spring-boot)
    to represent the information transferred outside the system (e.g. as *JSON*).

The pattern originates from the book: *Deepak Alur, John Crupi and Dan Malks:*
[*"Core J2EE Patterns: Best Practices and Design Strategies"*](http://www.corej2eepatterns.com/Patterns2ndEd/BusinessObject.htm),
Prentice Hall / Sun Microsystems Press, ISBN:0131422464, 2nd Edition (June, 2003).

<table>
<td valign="top">
<img src="https://www.oracle.com/ocom/groups/public/@otn/documents/digitalasset/146804.jpg" width="300"/>
</td>
<td valign="top">
<img src="https://www.oracle.com/ocom/groups/public/@otn/documents/digitalasset/145996.jpg" width="300"/>
</td>
</table>


<!-- &nbsp; -->

## 2. *RowMapper* Interface

to follow.

