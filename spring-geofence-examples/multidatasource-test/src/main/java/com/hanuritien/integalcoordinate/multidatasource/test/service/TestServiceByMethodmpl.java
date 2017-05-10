package com.hanuritien.integalcoordinate.multidatasource.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hanuritien.integalcoordinate.multidatasource.DataSource;
import com.hanuritien.integalcoordinate.multidatasource.test.DataSourceName;
import com.hanuritien.integalcoordinate.multidatasource.test.repository.TestDao;

@Primary
@Service
@Qualifier("method")
public class TestServiceByMethodmpl implements TestService {

    @Autowired
    private TestDao testDao;

    @Override
    @DataSource(DataSourceName.DB1)
    @Transactional(readOnly = true)
    public List<String> db1Names() {
        return testDao.getNames();
    }

    @Override
    @DataSource(DataSourceName.DB2)
    @Transactional(readOnly = true)
    public List<String> db2Names() {
        return testDao.getNames();
    }

    @Override
    @DataSource(DataSourceName.DB3)
    @Transactional(readOnly = true)
    public List<String> db3Names() {
        return testDao.getNames();
    }

    @Override
    @DataSource(DataSourceName.DB4)
    @Transactional(readOnly = true)
    public List<String> db4Names() {
        return testDao.getNames();
    }
}
