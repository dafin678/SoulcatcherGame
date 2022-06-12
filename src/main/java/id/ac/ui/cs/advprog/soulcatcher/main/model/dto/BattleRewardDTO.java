package id.ac.ui.cs.advprog.soulcatcher.main.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class BattleRewardDTO {
    List<String> name;
    List<Integer> quantity;
}
