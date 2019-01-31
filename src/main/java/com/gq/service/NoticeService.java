package com.gq.service;

import com.gq.model.Notice;

public interface NoticeService {
    Notice selectByPrimaryKey(Integer id);

    int insert(Notice record);

    int deleteByPrimaryKey(Integer id);
}
