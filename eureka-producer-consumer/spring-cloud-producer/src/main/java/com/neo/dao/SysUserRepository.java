package com.neo.dao;

import com.neo.entity.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface SysUserRepository extends JpaRepository<SysUser, Long> {
    SysUser findByName(String name);

    boolean existsByName(String name);

    @Modifying
    @Transactional
    //@Query("delete from SysUser sy where sy.name = ?1")
    int deleteByName(String name);


}
