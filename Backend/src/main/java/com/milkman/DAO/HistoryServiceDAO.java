package com.milkman.DAO;

import com.milkman.DTO.MilkmanHistoryResponseDTO;
import com.milkman.repository.JdbcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public class HistoryServiceDAO {

    @Autowired
    JdbcRepository jdbc;

    private final RowMapper<MilkmanHistoryResponseDTO> historyMapper = new RowMapper<>() {
        @Override
        public MilkmanHistoryResponseDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            MilkmanHistoryResponseDTO o = new MilkmanHistoryResponseDTO();
            o.setOrder_id(UUID.fromString(rs.getString("order_id")));
            o.setQuantity(rs.getDouble("quantity"));
            o.setDue_amount(rs.getDouble("due_amount"));
            o.setMilk_rate(rs.getDouble("milk_rate"));
            o.setDelivery_status(rs.getString("delivery_status"));
            o.setDelivery_date(rs.getObject("delivery_date", LocalDate.class));
            return o;
        }
    };

    public List<MilkmanHistoryResponseDTO> getHistoryForGivenMilkmanAndCustomer(UUID milkmanId, UUID customerId, LocalDate from, LocalDate to) {
        String sql = """
                select
                    mo.id as order_id,
                    mo.amount as due_amount,
                    mo.quantity as quantity,
                    mo.rate as milk_rate,
                    oh.status as delivery_status,
                    oh.delivery_date as delivery_date
                from order_history oh
                    join milk_order mo
                    on oh.order_id = mo.id
                    join milkman_customer mc
                    on mo.milkman_customer_id = mc.id
                where mc.milkman_id = ?
                    and mc.customer_id = ?
                    and oh.delivery_date >= ? and oh.delivery_date < ?;
            """;
        return jdbc.query(sql, new Object[]{milkmanId, customerId, from, to}, historyMapper);
    }
}
