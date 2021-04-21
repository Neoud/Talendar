package com.example.talendar.data.tale;

import com.example.talendar.data.bean.Creation;

import java.util.ArrayList;
import java.util.List;

public interface TaleDataSource {

    List<Tale> getTaleByObjectId(String objectId);

}
