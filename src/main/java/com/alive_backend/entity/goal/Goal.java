package com.alive_backend.entity.goal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Goal {
    @Basic
    @Column(name = "user_id")
    private int userId;


    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "uuid", columnDefinition = "BINARY(16)")
    private UUID uuid;


    @Basic
    @Column(name = "goal_name")
    private String goalName;

    @Basic
    @Column(name = "goal_key1")
    private Double goalKey1;

    @Basic
    @Column(name = "goal_ddl")
    private Date goalDdl;

    @Basic
    @Column(name = "goal_key2")
    private String goalKey2;
     @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Goal goal = (Goal) o;

        if (userId != goal.userId) return false;
        if (!Objects.equals(uuid, goal.uuid)) return false;
        if (goalName != null ? !goalName.equals(goal.goalName) : goal.goalName != null) return false;
        if (goalKey1 != null ? !goalKey1.equals(goal.goalKey1) : goal.goalKey1 != null) return false;
        if (goalDdl != null ? !goalDdl.equals(goal.goalDdl) : goal.goalDdl != null) return false;
        if (goalKey2 != null ? !goalKey2.equals(goal.goalKey2) : goal.goalKey2 != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
       return Objects.hash(userId, uuid, goalName, goalKey1, goalDdl, goalKey2);
    }
}
