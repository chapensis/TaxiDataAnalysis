(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-2901"],{"7BsA":function(t,e,a){t.exports=function(t){function e(i){if(a[i])return a[i].exports;var n=a[i]={i:i,l:!1,exports:{}};return t[i].call(n.exports,n,n.exports,e),n.l=!0,n.exports}var a={};return e.m=t,e.c=a,e.i=function(t){return t},e.d=function(t,a,i){e.o(t,a)||Object.defineProperty(t,a,{configurable:!1,enumerable:!0,get:i})},e.n=function(t){var a=t&&t.__esModule?function(){return t.default}:function(){return t};return e.d(a,"a",a),a},e.o=function(t,e){return Object.prototype.hasOwnProperty.call(t,e)},e.p="/dist/",e(e.s=2)}([function(t,e,a){var i=a(4)(a(1),a(5),null,null);t.exports=i.exports},function(t,e,a){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var i=a(3);e.default={props:{startVal:{type:Number,required:!1,default:0},endVal:{type:Number,required:!1,default:2017},duration:{type:Number,required:!1,default:3e3},autoplay:{type:Boolean,required:!1,default:!0},decimals:{type:Number,required:!1,default:0,validator:function(t){return t>=0}},decimal:{type:String,required:!1,default:"."},separator:{type:String,required:!1,default:","},prefix:{type:String,required:!1,default:""},suffix:{type:String,required:!1,default:""},useEasing:{type:Boolean,required:!1,default:!0},easingFn:{type:Function,default:function(t,e,a,i){return a*(1-Math.pow(2,-10*t/i))*1024/1023+e}}},data:function(){return{localStartVal:this.startVal,displayValue:this.formatNumber(this.startVal),printVal:null,paused:!1,localDuration:this.duration,startTime:null,timestamp:null,remaining:null,rAF:null}},computed:{countDown:function(){return this.startVal>this.endVal}},watch:{startVal:function(){this.autoplay&&this.start()},endVal:function(){this.autoplay&&this.start()}},mounted:function(){this.autoplay&&this.start(),this.$emit("mountedCallback")},methods:{start:function(){this.localStartVal=this.startVal,this.startTime=null,this.localDuration=this.duration,this.paused=!1,this.rAF=(0,i.requestAnimationFrame)(this.count)},pauseResume:function(){this.paused?(this.resume(),this.paused=!1):(this.pause(),this.paused=!0)},pause:function(){(0,i.cancelAnimationFrame)(this.rAF)},resume:function(){this.startTime=null,this.localDuration=+this.remaining,this.localStartVal=+this.printVal,(0,i.requestAnimationFrame)(this.count)},reset:function(){this.startTime=null,(0,i.cancelAnimationFrame)(this.rAF),this.displayValue=this.formatNumber(this.startVal)},count:function(t){this.startTime||(this.startTime=t),this.timestamp=t;var e=t-this.startTime;this.remaining=this.localDuration-e,this.useEasing?this.countDown?this.printVal=this.localStartVal-this.easingFn(e,0,this.localStartVal-this.endVal,this.localDuration):this.printVal=this.easingFn(e,this.localStartVal,this.endVal-this.localStartVal,this.localDuration):this.countDown?this.printVal=this.localStartVal-(this.localStartVal-this.endVal)*(e/this.localDuration):this.printVal=this.localStartVal+(this.localStartVal-this.startVal)*(e/this.localDuration),this.countDown?this.printVal=this.printVal<this.endVal?this.endVal:this.printVal:this.printVal=this.printVal>this.endVal?this.endVal:this.printVal,this.displayValue=this.formatNumber(this.printVal),e<this.localDuration?this.rAF=(0,i.requestAnimationFrame)(this.count):this.$emit("callback")},isNumber:function(t){return!isNaN(parseFloat(t))},formatNumber:function(t){t=t.toFixed(this.decimals);var e=(t+="").split("."),a=e[0],i=e.length>1?this.decimal+e[1]:"",n=/(\d+)(\d{3})/;if(this.separator&&!this.isNumber(this.separator))for(;n.test(a);)a=a.replace(n,"$1"+this.separator+"$2");return this.prefix+a+i+this.suffix}},destroyed:function(){(0,i.cancelAnimationFrame)(this.rAF)}}},function(t,e,a){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var i=a(0),n=function(t){return t&&t.__esModule?t:{default:t}}(i);e.default=n.default,"undefined"!=typeof window&&window.Vue&&window.Vue.component("count-to",n.default)},function(t,e,a){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var i=0,n="webkit moz ms o".split(" "),o=void 0,r=void 0;if("undefined"==typeof window)e.requestAnimationFrame=o=function(){},e.cancelAnimationFrame=r=function(){};else{e.requestAnimationFrame=o=window.requestAnimationFrame,e.cancelAnimationFrame=r=window.cancelAnimationFrame;for(var s=void 0,l=0;l<n.length&&(!o||!r);l++)s=n[l],e.requestAnimationFrame=o=o||window[s+"RequestAnimationFrame"],e.cancelAnimationFrame=r=r||window[s+"CancelAnimationFrame"]||window[s+"CancelRequestAnimationFrame"];o&&r||(e.requestAnimationFrame=o=function(t){var e=(new Date).getTime(),a=Math.max(0,16-(e-i)),n=window.setTimeout(function(){t(e+a)},a);return i=e+a,n},e.cancelAnimationFrame=r=function(t){window.clearTimeout(t)})}e.requestAnimationFrame=o,e.cancelAnimationFrame=r},function(t,e){t.exports=function(t,e,a,i){var n,o=t=t||{},r=typeof t.default;"object"!==r&&"function"!==r||(n=t,o=t.default);var s="function"==typeof o?o.options:o;if(e&&(s.render=e.render,s.staticRenderFns=e.staticRenderFns),a&&(s._scopeId=a),i){var l=Object.create(s.computed||null);Object.keys(i).forEach(function(t){var e=i[t];l[t]=function(){return e}}),s.computed=l}return{esModule:n,exports:o,options:s}}},function(t,e){t.exports={render:function(){var t=this,e=t.$createElement;return(t._self._c||e)("span",[t._v("\n  "+t._s(t.displayValue)+"\n")])},staticRenderFns:[]}}])},HESE:function(t,e,a){},"Uk2+":function(t,e,a){"use strict";var i=a("HESE");a.n(i).a},XTD8:function(t,e,a){"use strict";a.r(e);var i=a("QbLZ"),n=a.n(i),o=a("7BsA"),r=a.n(o),s=a("L2JU"),l={name:"RoadList",components:{CountTo:r.a},data:function(){return{queryStartRoadName:"",queryEndRoadName:"",queryTimeslot:"",pageNum:1,pageSize:10,isLoading:!0,loadingText:"",multipleSelectedPosition:[]}},computed:n()({},Object(s.d)("road",["roadSeekingTripList"])),created:function(){this.handleGetRoadSeekingTripList()},methods:n()({},Object(s.b)("road",["getRoadSeekingTripList"]),{handleSetLineChartData:function(t){this.$emit("handleSetLineChartData",t)},handleGetRoadSeekingTripList:function(){var t=this;this.loadingText="正在加载寻客路段数据",this.isLoading=!0,this.getRoadSeekingTripList({pagination:{pageNum:this.pageNum,pageSize:this.pageSize}}).then(function(){t.isLoading=!1}).catch(function(e){t.isLoading=!1,"string"==typeof e&&"cancel"!==e&&t.$message.error(e)})},handleSelectionChange:function(t){this.multipleSelectedPosition=t},handleSizeChange:function(t){this.pageSize=t,this.handleGetRoadSeekingTripList()},handleCurrentChange:function(t){this.pageNum=t,this.handleGetRoadSeekingTripList()},showRoadDetail:function(t){this.$message.info("功能正在开发中....")},showMultiRoadDetail:function(){this.$message.info("功能正在开发中....")},showRoadPanorama:function(t){this.$router.push({name:"RoadMapPanorama",params:{roadLon:t.roadLon,roadLat:t.roadLat}})},filter:function(){var t=this;this.loadingText="正在加载寻客路段数据",this.isLoading=!0,this.getRoadSeekingTripList({pagination:{pageNum:this.pageNum,pageSize:this.pageSize},timeslot:this.queryTimeslot,startRoadInfo:{roadName:this.queryStartRoadName},endRoadInfo:{roadName:this.queryEndRoadName}}).then(function(){t.isLoading=!1}).catch(function(e){t.isLoading=!1,"string"==typeof e&&"cancel"!==e&&t.$message.error(e)})}})},u=(a("Uk2+"),a("KHd+")),d=Object(u.a)(l,function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{attrs:{id:"road-list"}},[a("div",{staticClass:"road-list"},[[t._v("\n      起点路段名：\n      "),a("el-input",{staticClass:"input-with-select",attrs:{placeholder:"请输入查询起点路段名","prefix-icon":"el-icon-search",size:"mini"},model:{value:t.queryStartRoadName,callback:function(e){t.queryStartRoadName=e},expression:"queryStartRoadName"}}),t._v("\n      终点路段名：\n      "),a("el-input",{staticClass:"input-with-select",attrs:{placeholder:"请输入查询终点路段名","prefix-icon":"el-icon-search",size:"mini"},model:{value:t.queryEndRoadName,callback:function(e){t.queryEndRoadName=e},expression:"queryEndRoadName"}}),t._v("\n      时间段：\n      "),a("el-input",{staticClass:"input-with-select",attrs:{placeholder:"请输入查询终点路段名","prefix-icon":"el-icon-search",size:"mini"},model:{value:t.queryTimeslot,callback:function(e){t.queryTimeslot=e},expression:"queryTimeslot"}}),t._v(" "),a("el-button",{attrs:{type:"primary",size:"mini"},on:{click:t.filter}},[t._v("搜索")]),t._v(" "),a("el-button",{attrs:{type:"danger",size:"mini"},on:{click:t.showMultiRoadDetail}},[t._v("批量定位")])],t._v(" "),a("div",{staticStyle:{"margin-top":"15px"}}),t._v(" "),[a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:t.isLoading,expression:"isLoading"}],staticStyle:{width:"100%"},attrs:{data:t.roadSeekingTripList,"element-loading-text":t.loadingText,"default-sort":{prop:"tripId",order:"descending"},"element-loading-spinner":"el-icon-loading","element-loading-background":"rgba(0, 0, 0, 0.5)",stripe:"",border:"","highlight-current-row":"",fit:"","row-class-name":"align-center","header-cell-class-name":"align-center"},on:{"selection-change":t.handleSelectionChange}},[a("el-table-column",{attrs:{type:"expand"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("el-form",{staticClass:"demo-table-expand",attrs:{"label-position":"left",inline:""}},[a("el-form-item",{attrs:{label:"起点路段Id"}},[a("span",[t._v(t._s(e.row.startRoadInfo.roadId))])]),t._v(" "),a("el-form-item",{attrs:{label:"终点路段Id"}},[a("span",[t._v(t._s(e.row.endRoadInfo.roadId))])])],1)]}}])}),t._v(" "),a("el-table-column",{attrs:{type:"selection",width:"55"}}),t._v(" "),a("el-table-column",{attrs:{label:"寻客Id",width:"80"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("span",[t._v(t._s(e.row.tripId))])]}}])}),t._v(" "),a("el-table-column",{attrs:{label:"下车路段名",width:"120"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("span",[t._v(t._s(e.row.startRoadInfo.roadName))])]}}])}),t._v(" "),a("el-table-column",{attrs:{label:"上车路段名",width:"120"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("span",[t._v(t._s(e.row.endRoadInfo.roadName))])]}}])}),t._v(" "),a("el-table-column",{attrs:{label:"时间段",width:"100"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("span",[t._v(t._s(e.row.timeslot))])]}}])}),t._v(" "),a("el-table-column",{attrs:{label:"总时间(分钟)"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("span",[t._v(t._s(e.row.totalTime))])]}}])}),t._v(" "),a("el-table-column",{attrs:{label:"总金钱(元)"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("span",[t._v(t._s(e.row.totalMoney))])]}}])}),t._v(" "),a("el-table-column",{attrs:{label:"总次数"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("span",[t._v(t._s(e.row.totalNum))])]}}])}),t._v(" "),a("el-table-column",{attrs:{fixed:"right",label:"操作",width:"180"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("el-button",{attrs:{size:"mini",type:"danger"},on:{click:function(a){t.showRoadDetail(e.row)}}},[t._v("\n              定位\n            ")])]}}])})],1)],t._v(" "),a("el-pagination",{attrs:{total:t.roadSeekingTripList&&t.roadSeekingTripList.length>0?t.roadSeekingTripList[0].pagination.total:0,"current-page":t.pageNum,"page-sizes":[5,10,15,20],"page-size":t.pageSize,background:"",layout:"total, sizes, prev, pager, next, jumper"},on:{"size-change":t.handleSizeChange,"current-change":t.handleCurrentChange}})],2)])},[],!1,null,null,null);d.options.__file="seekingtrip.vue";e.default=d.exports}}]);