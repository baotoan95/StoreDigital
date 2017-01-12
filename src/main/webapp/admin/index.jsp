<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script>
(function(w,d,s,g,js,fs){
  g=w.gapi||(w.gapi={});g.analytics={q:[],ready:function(f){this.q.push(f);}};
  js=d.createElement(s);fs=d.getElementsByTagName(s)[0];
  js.src='https://apis.google.com/js/platform.js';
  fs.parentNode.insertBefore(js,fs);js.onload=function(){g.load('analytics');};
}(window,document,'script'));
</script>

<!-- This demo uses the Chart.js graphing library and Moment.js to do date
     formatting and manipulation. -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/1.0.2/Chart.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.2/moment.min.js"></script>

<!-- Include the ViewSelector2 component script. -->
<script
	src="https://ga-dev-tools.appspot.com/public/javascript/embed-api/components/view-selector2.js"></script>

<!-- Include the DateRangeSelector component script. -->
<script
	src="https://ga-dev-tools.appspot.com/public/javascript/embed-api/components/date-range-selector.js"></script>

<!-- Include the ActiveUsers component script. -->
<script
	src="https://ga-dev-tools.appspot.com/public/javascript/embed-api/components/active-users.js"></script>


<!-- Include the CSS that styles the charts. -->
<link rel="stylesheet"
	href="https://ga-dev-tools.appspot.com/public/css/chartjs-visualizations.css">

<script type="text/javascript"
	src="<c:url value='/resources/admin/dist/js/canvasjs.min.js' />"></script>

<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>
		Dashboard <small>Control panel</small>
	</h1>
	<ol class="breadcrumb">
		<li><a href="index"><i class="fa fa-dashboard"></i>Trang chủ</a></li>
		<li class="active">Dashboard</li>
	</ol>
</section>

<!-- Main content -->
<section class="content">
	<!-- Small boxes (Stat box) -->
	<div class="row">
		<div class="col-lg-3 col-xs-6">
			<!-- small box -->
			<div class="small-box bg-aqua">
				<div class="inner">
					<h3>${sessionScope.totalOrder }</h3>
					<p>Tổng đơn hàng trong tháng</p>
				</div>
				<div class="icon">
					<i class="ion ion-bag"></i>
				</div>
				<a href="#" class="small-box-footer"> Chi tiết <i
					class="fa fa-arrow-circle-right"></i></a>
			</div>
		</div>
		<!-- ./col -->
		<div class="col-lg-3 col-xs-6">
			<!-- small box -->
			<div class="small-box bg-green">
				<div class="inner">
					<h3>374</h3>
					<p>Tổng lượt ghé thăm</p>
				</div>
				<div class="icon">
					<i class="ion ion-stats-bars"></i>
				</div>
				<a href="#" class="small-box-footer"> Chi tiết <i
					class="fa fa-arrow-circle-right"></i></a>
			</div>
		</div>
		<!-- ./col -->
		<div class="col-lg-3 col-xs-6">
			<!-- small box -->
			<div class="small-box bg-yellow">
				<div class="inner">
					<h3>${sessionScope.totalUser }</h3>
					<p>Tổng số thành viên</p>
				</div>
				<div class="icon">
					<i class="ion ion-person-add"></i>
				</div>
				<a href="#" class="small-box-footer"> Chi tiết <i
					class="fa fa-arrow-circle-right"></i></a>
			</div>
		</div>
		<!-- ./col -->
		<div class="col-lg-3 col-xs-6">
			<!-- small box -->
			<div class="small-box bg-red">
				<div class="inner">
					<h3>${sessionScope.totalProduct }</h3>
					<p>Tổng số sản phẩm</p>
				</div>
				<div class="icon">
					<i class="ion ion-pie-graph"></i>
				</div>
				<a href="#" class="small-box-footer"> Chi tiết <i
					class="fa fa-arrow-circle-right"></i></a>
			</div>
		</div>
		<!-- ./col -->
	</div>
	<!-- /.row -->

	<div class="row">
		<div class="col-md-12">
			<!-- Custom Tabs -->
			<div class="nav-tabs-custom">
				<ul class="nav nav-tabs">
					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" href="#" aria-expanded="false">
							Thống kê số lượng đơn hàng <span class="caret"></span>
					</a>
						<ul class="dropdown-menu">
							<li role="presentation"><a role="menuitem" tabindex="-1">Trong tháng</a></li>
							<li>
								<select onclick="getChartByMonth()" id="month">
									<% for(int i = 1; i <= 12; i++) { %>
									<option value="<%= i%>"><%= i %></option>
									<%} %>
								</select>
							</li>
							<li role="presentation"><a role="menuitem" tabindex="-1">Trong năm</a></li>
							<li>
								<select onclick="getChartByYear()" id="year">
									<% for(int i = 2010; i <= 2017; i++) { %>
									<option value="<%= i%>"><%= i %></option>
									<%} %>
								</select>
							</li>
						</ul>
					</li>
					
				</ul>
				
				<div class="tab-content">
					<div class="tab-pane active" id="tab_1">
						<div id="chartContainer" style="height: 400px; width: 100%;"></div>

						<script type="text/javascript">
						function getChartByMonth() {
							var date = new Date();
							$.ajax({
								url : "/StoreDigital/orderChartByMonth",
								type : 'GET',
								dataType : 'json',
								data : {
									"month" : $('#month').val(),
									"year" : $('#year').val()
								},
								contentType : 'application/json',
								mimeType : 'application/json',
								success : function(data) {
									chart(data.content);
								}, error: function(err) {
									alert("Không có dữ liệu");
								}
							});
						};
						
						function getChartByYear() {
							var date = new Date();
							$.ajax({
								url : "/StoreDigital/orderChartByYear",
								type : 'GET',
								dataType : 'json',
								data : {
									"year" : 2015
								},
								contentType : 'application/json',
								mimeType : 'application/json',
								success : function(data) {
									chart(data.content);
								}, error: function(err) {
									alert("error");
								}
							});
						};
						
						function chart(data) {
							var chart = new CanvasJS.Chart("chartContainer", {
								title: {
									text: "THỐNG KÊ SỐ LƯỢNG ĐƠN HÀNG"
								},
								data: [{
									type: "column",
									dataPoints: $.parseJSON(data)
								}]
							});
							chart.render();
						}
						</script>
					</div>
				</div>
			</div>
			<!-- /.col -->
		</div>
	</div>

	<!-- Main row -->
	<div class="row">
		<div class="col-md-12">
			<div class="box box-danger">
				<header>
					<div id="embed-api-auth-container"></div>
					<div id="view-selector-container"></div>
					<div id="view-name"></div>
					<div id="active-users-container"></div>
				</header>
				<div class="Chartjs">
					<h3>This Week vs Last Week (by sessions)</h3>
					<figure class="Chartjs-figure" id="chart-1-container"></figure>
					<ol class="Chartjs-legend" id="legend-1-container"></ol>
				</div>
				<div class="Chartjs">
					<h3>This Year vs Last Year (by users)</h3>
					<figure class="Chartjs-figure" id="chart-2-container"></figure>
					<ol class="Chartjs-legend" id="legend-2-container"></ol>
				</div>
				<div class="Chartjs">
					<h3>Top Browsers (by pageview)</h3>
					<figure class="Chartjs-figure" id="chart-3-container"></figure>
					<ol class="Chartjs-legend" id="legend-3-container"></ol>
				</div>
				<div class="Chartjs">
					<h3>Top Countries (by sessions)</h3>
					<figure class="Chartjs-figure" id="chart-4-container"></figure>
					<ol class="Chartjs-legend" id="legend-4-container"></ol>
				</div>
			</div>
			<!-- /.box -->
		</div>

		<script>

		// == NOTE ==
		// This code uses ES6 promises. If you want to use this code in a browser
		// that doesn't supporting promises natively, you'll have to include a polyfill.
		
		gapi.analytics.ready(function() {
		
		  /**
		   * Authorize the user immediately if the user has already granted access.
		   * If no access has been created, render an authorize button inside the
		   * element with the ID "embed-api-auth-container".
		   */
		  gapi.analytics.auth.authorize({
		    container: 'embed-api-auth-container',
		    clientid: '758637087594-mm4vdgqbc867hjgav5juamv1oul55ooi.apps.googleusercontent.com'
		  });
		
		
		  /**
		   * Create a new ActiveUsers instance to be rendered inside of an
		   * element with the id "active-users-container" and poll for changes every
		   * five seconds.
		   */
		  var activeUsers = new gapi.analytics.ext.ActiveUsers({
		    container: 'active-users-container',
		    pollingInterval: 5
		  });
		
		
		  /**
		   * Add CSS animation to visually show the when users come and go.
		   */
		  activeUsers.once('success', function() {
		    var element = this.container.firstChild;
		    var timeout;
		
		    this.on('change', function(data) {
		      var element = this.container.firstChild;
		      var animationClass = data.delta > 0 ? 'is-increasing' : 'is-decreasing';
		      element.className += (' ' + animationClass);
		
		      clearTimeout(timeout);
		      timeout = setTimeout(function() {
		        element.className =
		            element.className.replace(/ is-(increasing|decreasing)/g, '');
		      }, 3000);
		    });
		  });
		
		
		  /**
		   * Create a new ViewSelector2 instance to be rendered inside of an
		   * element with the id "view-selector-container".
		   */
		  var viewSelector = new gapi.analytics.ext.ViewSelector2({
		    container: 'view-selector-container',
		  })
		  .execute();
		
		
		  /**
		   * Update the activeUsers component, the Chartjs charts, and the dashboard
		   * title whenever the user changes the view.
		   */
		  viewSelector.on('viewChange', function(data) {
		    var title = document.getElementById('view-name');
		    title.innerHTML = data.property.name + ' (' + data.view.name + ')';
		
		    // Start tracking active users for this view.
		    activeUsers.set(data).execute();
		
		    // Render all the of charts for this view.
		    renderWeekOverWeekChart(data.ids);
		    renderYearOverYearChart(data.ids);
		    renderTopBrowsersChart(data.ids);
		    renderTopCountriesChart(data.ids);
		  });
		
		
		  /**
		   * Draw the a chart.js line chart with data from the specified view that
		   * overlays session data for the current week over session data for the
		   * previous week.
		   */
		  function renderWeekOverWeekChart(ids) {
		
		    // Adjust `now` to experiment with different days, for testing only...
		    var now = moment(); // .subtract(3, 'day');
		
		    var thisWeek = query({
		      'ids': ids,
		      'dimensions': 'ga:date,ga:nthDay',
		      'metrics': 'ga:sessions',
		      'start-date': moment(now).subtract(1, 'day').day(0).format('YYYY-MM-DD'),
		      'end-date': moment(now).format('YYYY-MM-DD')
		    });
		
		    var lastWeek = query({
		      'ids': ids,
		      'dimensions': 'ga:date,ga:nthDay',
		      'metrics': 'ga:sessions',
		      'start-date': moment(now).subtract(1, 'day').day(0).subtract(1, 'week')
		          .format('YYYY-MM-DD'),
		      'end-date': moment(now).subtract(1, 'day').day(6).subtract(1, 'week')
		          .format('YYYY-MM-DD')
		    });
		
		    Promise.all([thisWeek, lastWeek]).then(function(results) {
		
		      var data1 = results[0].rows.map(function(row) { return +row[2]; });
		      var data2 = results[1].rows.map(function(row) { return +row[2]; });
		      var labels = results[1].rows.map(function(row) { return +row[0]; });
		
		      labels = labels.map(function(label) {
		        return moment(label, 'YYYYMMDD').format('ddd');
		      });
		
		      var data = {
		        labels : labels,
		        datasets : [
		          {
		            label: 'Last Week',
		            fillColor : 'rgba(220,220,220,0.5)',
		            strokeColor : 'rgba(220,220,220,1)',
		            pointColor : 'rgba(220,220,220,1)',
		            pointStrokeColor : '#fff',
		            data : data2
		          },
		          {
		            label: 'This Week',
		            fillColor : 'rgba(151,187,205,0.5)',
		            strokeColor : 'rgba(151,187,205,1)',
		            pointColor : 'rgba(151,187,205,1)',
		            pointStrokeColor : '#fff',
		            data : data1
		          }
		        ]
		      };
		
		      new Chart(makeCanvas('chart-1-container')).Line(data);
		      generateLegend('legend-1-container', data.datasets);
		    });
		  }
		
		
		  /**
		   * Draw the a chart.js bar chart with data from the specified view that
		   * overlays session data for the current year over session data for the
		   * previous year, grouped by month.
		   */
		  function renderYearOverYearChart(ids) {
		
		    // Adjust `now` to experiment with different days, for testing only...
		    var now = moment(); // .subtract(3, 'day');
		
		    var thisYear = query({
		      'ids': ids,
		      'dimensions': 'ga:month,ga:nthMonth',
		      'metrics': 'ga:users',
		      'start-date': moment(now).date(1).month(0).format('YYYY-MM-DD'),
		      'end-date': moment(now).format('YYYY-MM-DD')
		    });
		
		    var lastYear = query({
		      'ids': ids,
		      'dimensions': 'ga:month,ga:nthMonth',
		      'metrics': 'ga:users',
		      'start-date': moment(now).subtract(1, 'year').date(1).month(0)
		          .format('YYYY-MM-DD'),
		      'end-date': moment(now).date(1).month(0).subtract(1, 'day')
		          .format('YYYY-MM-DD')
		    });
		
		    Promise.all([thisYear, lastYear]).then(function(results) {
		      var data1 = results[0].rows.map(function(row) { return +row[2]; });
		      var data2 = results[1].rows.map(function(row) { return +row[2]; });
		      var labels = ['Jan','Feb','Mar','Apr','May','Jun',
		                    'Jul','Aug','Sep','Oct','Nov','Dec'];
		
		      // Ensure the data arrays are at least as long as the labels array.
		      // Chart.js bar charts don't (yet) accept sparse datasets.
		      for (var i = 0, len = labels.length; i < len; i++) {
		        if (data1[i] === undefined) data1[i] = null;
		        if (data2[i] === undefined) data2[i] = null;
		      }
		
		      var data = {
		        labels : labels,
		        datasets : [
		          {
		            label: 'Last Year',
		            fillColor : 'rgba(220,220,220,0.5)',
		            strokeColor : 'rgba(220,220,220,1)',
		            data : data2
		          },
		          {
		            label: 'This Year',
		            fillColor : 'rgba(151,187,205,0.5)',
		            strokeColor : 'rgba(151,187,205,1)',
		            data : data1
		          }
		        ]
		      };
		
		      new Chart(makeCanvas('chart-2-container')).Bar(data);
		      generateLegend('legend-2-container', data.datasets);
		    })
		    .catch(function(err) {
		      console.error(err.stack);
		    });
		  }
		
		
		  /**
		   * Draw the a chart.js doughnut chart with data from the specified view that
		   * show the top 5 browsers over the past seven days.
		   */
		  function renderTopBrowsersChart(ids) {
		
		    query({
		      'ids': ids,
		      'dimensions': 'ga:browser',
		      'metrics': 'ga:pageviews',
		      'sort': '-ga:pageviews',
		      'max-results': 5
		    })
		    .then(function(response) {
		
		      var data = [];
		      var colors = ['#4D5360','#949FB1','#D4CCC5','#E2EAE9','#F7464A'];
		
		      response.rows.forEach(function(row, i) {
		        data.push({ value: +row[1], color: colors[i], label: row[0] });
		      });
		
		      new Chart(makeCanvas('chart-3-container')).Doughnut(data);
		      generateLegend('legend-3-container', data);
		    });
		  }
		
		
		  /**
		   * Draw the a chart.js doughnut chart with data from the specified view that
		   * compares sessions from mobile, desktop, and tablet over the past seven
		   * days.
		   */
		  function renderTopCountriesChart(ids) {
		    query({
		      'ids': ids,
		      'dimensions': 'ga:country',
		      'metrics': 'ga:sessions',
		      'sort': '-ga:sessions',
		      'max-results': 5
		    })
		    .then(function(response) {
		
		      var data = [];
		      var colors = ['#4D5360','#949FB1','#D4CCC5','#E2EAE9','#F7464A'];
		
		      response.rows.forEach(function(row, i) {
		        data.push({
		          label: row[0],
		          value: +row[1],
		          color: colors[i]
		        });
		      });
		
		      new Chart(makeCanvas('chart-4-container')).Doughnut(data);
		      generateLegend('legend-4-container', data);
		    });
		  }
		
		
		  /**
		   * Extend the Embed APIs `gapi.analytics.report.Data` component to
		   * return a promise the is fulfilled with the value returned by the API.
		   * @param {Object} params The request parameters.
		   * @return {Promise} A promise.
		   */
		  function query(params) {
		    return new Promise(function(resolve, reject) {
		      var data = new gapi.analytics.report.Data({query: params});
		      data.once('success', function(response) { resolve(response); })
		          .once('error', function(response) { reject(response); })
		          .execute();
		    });
		  }
		
		
		  /**
		   * Create a new canvas inside the specified element. Set it to be the width
		   * and height of its container.
		   * @param {string} id The id attribute of the element to host the canvas.
		   * @return {RenderingContext} The 2D canvas context.
		   */
		  function makeCanvas(id) {
		    var container = document.getElementById(id);
		    var canvas = document.createElement('canvas');
		    var ctx = canvas.getContext('2d');
		
		    container.innerHTML = '';
		    canvas.width = container.offsetWidth;
		    canvas.height = container.offsetHeight;
		    container.appendChild(canvas);
		
		    return ctx;
		  }
		
		
		  /**
		   * Create a visual legend inside the specified element based off of a
		   * Chart.js dataset.
		   * @param {string} id The id attribute of the element to host the legend.
		   * @param {Array.<Object>} items A list of labels and colors for the legend.
		   */
		  function generateLegend(id, items) {
		    var legend = document.getElementById(id);
		    legend.innerHTML = items.map(function(item) {
		      var color = item.color || item.fillColor;
		      var label = item.label;
		      return '<li><i style="background:' + color + '"></i>' + label + '</li>';
		    }).join('');
		  }
		
		
		  // Set some global Chart.js defaults.
		  Chart.defaults.global.animationSteps = 60;
		  Chart.defaults.global.animationEasing = 'easeInOutQuart';
		  Chart.defaults.global.responsive = true;
		  Chart.defaults.global.maintainAspectRatio = false;
		
		});
		</script>

		<!-- Left col -->
		<section class="col-lg-7 connectedSortable">
			<!-- quick email widget -->
			<div class="box box-info">
				<div class="box-header">
					<i class="fa fa-envelope"></i>
					<h3 class="box-title">Gửi email</h3>
					<!-- tools box -->
					<div class="pull-right box-tools">
						<button class="btn btn-info btn-sm" data-widget="remove"
							data-toggle="tooltip" title="Remove">
							<i class="fa fa-times"></i>
						</button>
					</div>
					<!-- /. tools -->
				</div>
				<div class="box-body">
					<form action="#" method="post">
						<div class="form-group">
							<input type="email" class="form-control" name="emailto"
								placeholder="Email to:" />
						</div>
						<div class="form-group">
							<input type="text" class="form-control" name="subject"
								placeholder="Subject" />
						</div>
						<div>
							<textarea class="textarea" placeholder="Message"
								style="width: 100%; height: 125px; font-size: 14px; line-height: 18px; border: 1px solid #dddddd; padding: 10px;"></textarea>
						</div>
					</form>
				</div>
				<div class="box-footer clearfix">
					<button class="pull-right btn btn-default" id="sendEmail">
						Send <i class="fa fa-arrow-circle-right"></i>
					</button>
				</div>
			</div>
		</section>
		<!-- /.Left col -->
		<!-- right col (We are only adding the ID to make the widgets sortable)-->
		<section class="col-lg-5 connectedSortable">
			<!-- Calendar -->
			<div class="box box-solid bg-green-gradient">
				<div class="box-header">
					<i class="fa fa-calendar"></i>
					<h3 class="box-title">Lịch</h3>
					<!-- tools box -->
					<div class="pull-right box-tools">
						<!-- button with a dropdown -->
						<button class="btn btn-success btn-sm" data-widget="collapse">
							<i class="fa fa-minus"></i>
						</button>
						<button class="btn btn-success btn-sm" data-widget="remove">
							<i class="fa fa-times"></i>
						</button>
					</div>
					<!-- /. tools -->
				</div>
				<!-- /.box-header -->
				<div class="box-body no-padding">
					<!--The calendar -->
					<div id="calendar" style="width: 100%"></div>
				</div>
			</div>
			<!-- /.box -->
		</section>
		<!-- right col -->
	</div>
	<!-- /.row (main row) -->
</section>
<!-- /.content -->

<!-- Add the sidebar's background. This div must be placed
           immediately after the control sidebar -->
<div class="control-sidebar-bg"></div>
