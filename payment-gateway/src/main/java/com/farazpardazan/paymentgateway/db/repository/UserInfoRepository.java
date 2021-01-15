/*
 * Copyright (c) 2019 TRIALBLAZE PTY. LTD. All rights reserved.
 *
 * Created by IntelliJ IDEA.
 * @author: Hamed Yousefi <hamed.yousefi@trialblaze.com>
 * @since: 1/14/21
 */
package com.farazpardazan.paymentgateway.db.repository;

import com.farazpardazan.paymentgateway.db.model.UserInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserInfoRepository extends CrudRepository<UserInfo, UUID> {
}
