/*
 * The contents of this file are subject to the OpenMRS Public License
 * Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See
 * the License for the specific language governing rights and
 * limitations under the License.
 *
 * Copyright (C) OpenHMIS.  All Rights Reserved.
 */
package org.openmrs.module.visittasks.api.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.openmrs.Patient;
import org.openmrs.Visit;
import org.openmrs.module.openhmis.commons.api.PagingInfo;
import org.openmrs.module.openhmis.commons.api.entity.impl.BaseEntityDataServiceImpl;
import org.openmrs.module.openhmis.commons.api.entity.security.IEntityAuthorizationPrivileges;
import org.openmrs.module.openhmis.commons.api.f.Action1;
import org.openmrs.module.visittasks.api.IVisitTaskDataService;
import org.openmrs.module.visittasks.api.model.VisitTask;
import org.openmrs.module.visittasks.api.model.VisitTaskStatus;
import org.openmrs.module.visittasks.api.util.PrivilegeConstants;
import org.openmrs.module.visittasks.api.util.VisitTasksHibernateCriteriaConstants;

import java.util.List;

/**
 * Data service implementation class for {@link VisitTask}s.
 */
public class VisitTaskDataServiceImpl extends BaseEntityDataServiceImpl<VisitTask> implements
        IEntityAuthorizationPrivileges, IVisitTaskDataService {

	@Override
	protected IEntityAuthorizationPrivileges getPrivileges() {
		return this;
	}

	@Override
	protected void validate(VisitTask object) {
		return;
	}

	@Override
	public List<VisitTask> getVisitTasks(
	        final VisitTaskStatus status, final Visit visit, PagingInfo pagingInfo) {
		if (pagingInfo == null) {
			throw new IllegalArgumentException("pagingInfo must not be null");
		}

		return executeCriteria(VisitTask.class, pagingInfo, new Action1<Criteria>() {
			@Override
			public void apply(Criteria criteria) {
				if (status == null) {
					criteria.addOrder(Order.desc(VisitTasksHibernateCriteriaConstants.DATE_CREATED));
				} else {
					criteria.add(Restrictions.eq(VisitTasksHibernateCriteriaConstants.STATUS, status));

					if (status.equals(VisitTaskStatus.CLOSED)) {
						criteria.addOrder(Order.desc(VisitTasksHibernateCriteriaConstants.CLOSED_ON));
					} else {
						criteria.addOrder(Order.desc(VisitTasksHibernateCriteriaConstants.DATE_CREATED));
					}
				}

				if (visit != null) {
					criteria.add(Restrictions.eq(VisitTasksHibernateCriteriaConstants.VISIT, visit));
				}

				criteria.add(Restrictions.eq(VisitTasksHibernateCriteriaConstants.VOIDED, false));
			}
		});

	}

	@Override
	public String getVoidPrivilege() {
		return PrivilegeConstants.TASK_MANAGE_VISIT_TASK_METADATA;
	}

	@Override
	public String getSavePrivilege() {
		return PrivilegeConstants.TASK_MANAGE_VISIT_TASK_METADATA;
	}

	@Override
	public String getPurgePrivilege() {
		return PrivilegeConstants.TASK_MANAGE_VISIT_TASK_METADATA;
	}

	@Override
	public String getGetPrivilege() {
		return PrivilegeConstants.TASK_MANAGE_VISIT_TASK_METADATA;
	}
}
