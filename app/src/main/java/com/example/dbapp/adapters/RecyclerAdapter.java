package com.example.dbapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dbapp.R;
import com.example.dbapp.models.StudentModels;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private final List<StudentModels> studentList;

    public RecyclerAdapter(List<StudentModels> studentList) {
        this.studentList = studentList;
    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_details_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
        String reg_no, name, bDay, email;
        reg_no = "Registraion No : " + studentList.get(position).getReg_no();
        name = "Full Name : " + studentList.get(position).getfName();
        bDay = "Birthday : " + studentList.get(position).getbDay();
        email = "Email Address : " + studentList.get(position).getEmail();
        holder.setItemData(reg_no,name,bDay,email);
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView reg_no, fName, bDay, email;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            reg_no = itemView.findViewById(R.id.tv_reg_no);
            fName = itemView.findViewById(R.id.tv_fullname);
            bDay = itemView.findViewById(R.id.tv_birthday);
            email = itemView.findViewById(R.id.tv_email);
        }

        public void setItemData(String _reg_no, String _fName, String _bDay,String _email){
            this.reg_no.setText(_reg_no);
            this.fName.setText(_fName);
            this.bDay.setText(_bDay);
            this.email.setText(_email);
        }
    }
}
