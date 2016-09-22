
<form name="entityForm" class="entity-form" ng-class="{'submitted': submitted}" style="font-size:inherit">
	<span class="h1-substitue-left">
		${ui.message('visittasks.predefinedTask.new')}
	</span>
	
	<fieldset class="format">
		<div class="row">
			<div class="col-md-2">
				<span style="color: red">*</span><span>&nbsp;${ui.message('general.name')}</span>
			</div>
			<div class="col-md-10">
				<input name="entityName" class="form-control" type="text" ng-model="entity.name" class="maximized"
				       placeholder="${ui.message('general.name')}" required/>
				
				<p class="checkRequired"
				   ng-hide="nameIsRequiredMsg == '' || nameIsRequiredMsg == undefined">{{nameIsRequiredMsg}}</p>
			</div>
		</div>
		<br/>
		<div class="row" ng-show="showMakeGlobal == true">
			<div class="col-md-2">
				<span>&nbsp;&nbsp;&nbsp;${ui.message('visittasks.predefinedTask.makeGlobal.label')}</span>
			</div>
			<div class="col-md-10">
				<input type="checkbox" ng-model="entity.global" name="global" ng-checked="entity.global"/>
			</div>
		</div>
	</fieldset>
	<fieldset class="format">
		<span>
			<input type="button" class="cancel" value="${ui.message('general.cancel')}" ng-click="cancel()"/>
			<input type="button" class="confirm right" value="${ui.message('general.save')}" ng-click="saveOrUpdate()"/>
		</span>
	</fieldset>
</form>
