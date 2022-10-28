package com.edu_cms.service.impl;

import com.edu_cms.entity.CrmBanner;
import com.edu_cms.mapper.CrmBannerMapper;
import com.edu_cms.service.CrmBannerService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-03-07
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {


    /*
      查询所有banner,加入redis缓存,key是banner::selectIndexList,value是返回结果
      拓展:
         注解 @CacheEvict(value = "banner",allEntries = true) 意思是清除banner下的所有key 一般用在更新或者删除方法上
    */
    @Cacheable(value = "banner",key = "'selectIndexList'")
    @Override
    public List<CrmBanner> selectAllBanner() {

        //根据id进行降序排列,显示排列之后前两条记录
        QueryWrapper<CrmBanner> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        //last方法,拼接sql语句
        wrapper.last("limit 2");
        List<CrmBanner> list = baseMapper.selectList(null);
        return list;
    }
}
