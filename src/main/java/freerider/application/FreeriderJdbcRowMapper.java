package freerider.application;

import java.sql.ResultSet;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.RowMapper;

import freerider.dao.Customer;
import freerider.dao.Reservation;
import freerider.dao.Vehicle;


@Component
public class FreeriderJdbcRowMapper {

    /*
     * Spring-initialized variable ("autowired")
     */
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * RowMapper<Customer> to map {@link ResultSet} {@code rs} to {@link Customer} dao.
     */
    private final RowMapper<Customer> customerRowMapper = (rs, rowNum) -> {
        // 
        int id = rs.getInt("ID");
        String name = rs.getString("NAME");
        String contact = rs.getString("CONTACT");
        Customer.Status status = Customer.Status.valueOf(rs.getString("STATUS"));
        // 
        return new Customer(id, name, contact, status);
    };

    /**
     * RowMapper<Vehicle> to map {@link ResultSet} {@code rs} to {@link Vehicle} dao.
     */
    private final RowMapper<Vehicle> vehicleRowMapper = (rs, rowNum) -> {
        // 
        int id = rs.getInt("ID");
        String make = rs.getString("MAKE");
        String model = rs.getString("MODEL");
        int seats = rs.getInt("SEATS");
        Vehicle.Category category = Vehicle.Category.valueOf(rs.getString("CATEGORY"));
        Vehicle.Power power = Vehicle.Power.valueOf(rs.getString("POWER"));
        Vehicle.Status status = Vehicle.Status.valueOf(rs.getString("STATUS"));
        // 
        return new Vehicle(id, make, model, seats, category, power, status);
    };

    /**
     * RowMapper<Reservation> to map {@link ResultSet} {@code rs} to {@link Reservation} dao.
     */
    private final RowMapper<Reservation> reservationRowMapper = (rs, rowNum) -> {
        // 
        int id = rs.getInt("ID");                   // PRIMARY KEY (ID)
        int customer_id = rs.getInt("CUSTOMER_ID"); // FOREIGN KEY (CUSTOMER_ID) REFERENCES CUSTOMER(ID)
        int vehicle_id = rs.getInt("VEHICLE_ID");   // FOREIGN KEY (VEHICLE_ID) REFERENCES VEHICLE(ID)
        LocalDateTime begin = rs.getTimestamp("RBEGIN").toLocalDateTime();
        LocalDateTime end = rs.getTimestamp("REND").toLocalDateTime();
        String pickup = rs.getString("PICKUP");
        String dropoff = rs.getString("DROPOFF");
        Reservation.Status status = Reservation.Status.valueOf(rs.getString("STATUS"));
        // 
        return new Reservation(id, customer_id, vehicle_id, begin, end, pickup, dropoff, status);
    };


    /**
     * Return all {@link Customer} objects from associated database table.
     * @return collection of {@link Customer} objects
     */
    public Iterable<Customer> findAllCustomers() {
        // 
        return jdbcTemplate.query("SELECT * FROM CUSTOMER", customerRowMapper);
    }

    /**
     * Return all {@link Vehicle} objects from associated database table.
     * @return collection of {@link Vehicle} objects
     */
    public Iterable<Vehicle> findAllVehicles() {
        // 
        return jdbcTemplate.query("SELECT * FROM VEHICLE", vehicleRowMapper);
    }

    /**
     * Return all {@link Reservation} objects from associated database table.
     * @return collection of {@link Reservation} objects
     */
    public Iterable<Reservation> findAllReservations() {
        // 
        return jdbcTemplate.query("SELECT * FROM RESERVATION", reservationRowMapper);
    }
}