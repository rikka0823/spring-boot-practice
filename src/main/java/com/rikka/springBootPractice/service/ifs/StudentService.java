package com.rikka.springBootPractice.service.ifs;

import com.rikka.springBootPractice.vo.StudentReq;
import com.rikka.springBootPractice.vo.StudentRes;

public interface StudentService {

    StudentRes basicRes();

    StudentRes basicRes(StudentReq req);
}
