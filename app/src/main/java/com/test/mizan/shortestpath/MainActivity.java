package com.test.mizan.shortestpath;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import com.test.mizan.shortestpath.implementation.CostManager;
import com.test.mizan.shortestpath.implementation.PathFinder;
import com.test.mizan.shortestpath.model.Graph;
import com.test.mizan.shortestpath.model.Output;
import com.test.mizan.shortestpath.parser.InputParser;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.edit_text_input) EditText edit_text_input;
    @BindView(R.id.text_view_error) TextView text_view_error;
    @BindView(R.id.text_view_completed) TextView text_view_completed;
    @BindView(R.id.text_view_total_cost) TextView text_view_total_cost;
    @BindView(R.id.text_view_path) TextView text_view_path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.btn_find_path)
    public void getResult() {
        try {
            InputParser inputParser = new InputParser();
            CostManager manager = new CostManager(new PathFinder(50));
            resetView();
            Graph graph = inputParser.parseInput(edit_text_input.getText().toString());
            Output output = manager.result(graph);
            text_view_completed.setText(output.isCompleted() ? "Yes" : "No");
            text_view_total_cost.setText(String.valueOf(output.getTotalCost()));
            text_view_path.setText("["+pathValue(output.getPath())+"]");
        } catch (RuntimeException e) {
            text_view_error.setVisibility(View.VISIBLE);
            text_view_error.setText(e.getMessage());
        }
    }

    private String pathValue(List<Integer> path) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < path.size(); i++) {
            sb.append(path.get(i));
            if (i != path.size() - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }
    private void resetView(){
        text_view_error.setVisibility(View.GONE);
        text_view_completed.setText("");
        text_view_total_cost.setText("");
        text_view_path.setText("");
    }
}
