package dao;

import model.Hotel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class HotelDAO extends AbstractDAO<Hotel> {

    @Override
    public List<Hotel> getAll() throws Exception {
        List<Hotel> hotels = new ArrayList<>();
        String sql = "SELECT * FROM hotels";
        try (PreparedStatement ps = getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                hotels.add(new Hotel(rs.getInt("id"), rs.getString("name"), rs.getString("address"), rs.getString("rooms")));
            }
        }
        return hotels;
    }

    @Override
    public Hotel getById(int id) throws Exception {
        String sql = "SELECT * FROM hotels WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Hotel(rs.getInt("id"), rs.getString("name"), rs.getString("address"), rs.getString("rooms"));
                }
            }
        }
        return null;
    }


    @Override
    public Hotel create(Hotel hotel) throws Exception {
        String sql = "INSERT INTO hotels (name, address, rooms) VALUES (?, ?, ?) RETURNING id";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, hotel.getName());
            ps.setString(2, hotel.getAddress());
            ps.setString(3, hotel.getRooms());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    hotel.setId(rs.getInt(1));
                    return hotel;
                }
            }
        }
        return null;
    }


    @Override
    public Hotel update(Hotel hotel) throws Exception {
        String sql = "UPDATE hotels SET name = ?, address = ?, rooms = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, hotel.getName());
            ps.setString(2, hotel.getAddress());
            ps.setString(3, hotel.getRooms());
            ps.setInt(4, hotel.getId());

            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                return hotel;
            }
        }
        return null;
    }

    @Override
    public void delete(int id) throws Exception {
        String sql = "DELETE FROM hotels WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    // Implement getById, create, update, delete...
}
