package dao;


import model.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO extends AbstractDAO<Room>{

    @Override
    public List<Room> getAll() throws Exception {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT * FROM rooms";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                rooms.add(new Room(rs.getInt("id"), rs.getInt("hotel_id"), rs.getInt("number"), rs.getString("price")));
            }
        }
        return rooms;
    }


    @Override
    public Room getById(int id) throws Exception {
        String sql = "SELECT * FROM rooms WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Room(rs.getInt("id"), rs.getInt("hotel_id"), rs.getInt("number"), rs.getString("price"));
                }
            }
        }
        return null;
    }

    @Override
    public Room create(Room room) throws Exception {
        String sql = "INSERT INTO rooms (hotel_id, number, price) VALUES (?, ?, ?) RETURNING id";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, room.getHotelId());
            ps.setInt(2, room.getNumber());
            ps.setString(3, room.getPrice());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    room.setId(rs.getInt(1));
                    return room;
                }
            }
        }
        return null;
    }

    @Override
    public Room update(Room room) throws Exception {
        String sql = "UPDATE rooms SET hotel_id = ?, number = ?, price = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, room.getHotelId());
            ps.setInt(2, room.getNumber());
            ps.setString(3, room.getPrice());
            ps.setInt(4, room.getId());

            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                return room;
            }
        }
        return null;
    }

    @Override
    public void delete(int id) throws Exception {
        String sql = "DELETE FROM rooms WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
