<template>
  <div id="road-list">
    <div class = "road-list">
      <!--查询条件-->
      <template>
        起点路段名：
        <el-input v-model="queryStartRoadName"
                  placeholder="请输入查询起点路段名"
                  prefix-icon="el-icon-search"
                  class="input-with-select"
                  size="mini" />
        终点路段名：
        <el-input v-model="queryEndRoadName"
                  placeholder="请输入查询终点路段名"
                  prefix-icon="el-icon-search"
                  class="input-with-select"
                  size="mini" />
        时间段：
        <el-input v-model="queryTimeslot"
                  placeholder="请输入查询终点路段名"
                  prefix-icon="el-icon-search"
                  class="input-with-select"
                  size="mini" />
        <el-button type="primary"
                   size="mini"
                   @click="filter">搜索</el-button>
        <el-button type="danger"
                   size="mini"
                   @click="showMultiRoadDetail">批量定位</el-button>
      </template>
      <div style="margin-top:15px;" />
      <template>
        <el-table
          v-loading="isLoading"
          :data="roadSeekingTripList"
          :element-loading-text="loadingText"
          :default-sort="{prop: 'tripId', order: 'descending'}"
          element-loading-spinner="el-icon-loading"
          element-loading-background="rgba(0, 0, 0, 0.5)"
          stripe
          border
          highlight-current-row
          fit
          row-class-name="align-center"
          header-cell-class-name="align-center"
          style="width: 100%"
          @selection-change="handleSelectionChange">
          <el-table-column type="expand">
            <template slot-scope="props">
              <el-form label-position="left" inline class="demo-table-expand">
                <el-form-item label="起点路段Id">
                  <span>{{ props.row.startRoadInfo.roadId }}</span>
                </el-form-item>
                <el-form-item label="终点路段Id">
                  <span>{{ props.row.endRoadInfo.roadId }}</span>
                </el-form-item>
              </el-form>
            </template>
          </el-table-column>
          <el-table-column type="selection" width="55" />
          <el-table-column label="寻客Id" width="80">
            <template slot-scope="scope">
              <span>{{ scope.row.tripId }}</span>
            </template>
          </el-table-column>
          <el-table-column label="下车路段名" width="120">
            <template slot-scope="scope">
              <span>{{ scope.row.startRoadInfo.roadName }}</span>
            </template>
          </el-table-column>
          <el-table-column label="上车路段名" width="120">
            <template slot-scope="scope">
              <span>{{ scope.row.endRoadInfo.roadName }}</span>
            </template>
          </el-table-column>
          <el-table-column label="时间段" width="100">
            <template slot-scope="scope">
              <span>{{ scope.row.timeslot }}</span>
            </template>
          </el-table-column>
          <el-table-column label="总时间(分钟)">
            <template slot-scope="scope">
              <span>{{ scope.row.totalTime }}</span>
            </template>
          </el-table-column>
          <el-table-column label="总金钱(元)">
            <template slot-scope="scope">
              <span>{{ scope.row.totalMoney }}</span>
            </template>
          </el-table-column>
          <el-table-column label="总次数">
            <template slot-scope="scope">
              <span>{{ scope.row.totalNum }}</span>
            </template>
          </el-table-column>
          <el-table-column fixed = "right" label = "操作" width = "180">
            <template slot-scope="scope">
              <el-button size="mini" type="danger" @click="showRoadDetail(scope.row)">
                定位
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </template>
      <el-pagination
        :total="roadSeekingTripList && roadSeekingTripList.length > 0 ? roadSeekingTripList[0].pagination.total : 0"
        :current-page="pageNum"
        :page-sizes="[5, 10, 15, 20]"
        :page-size="pageSize"
        background
        layout="total, sizes, prev, pager, next, jumper"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange" />
    </div>
  </div>
</template>

<script>
import CountTo from 'vue-count-to'
import { mapState, mapActions } from 'vuex'

export default {
  name: 'RoadList',
  components: {
    CountTo
  },
  data() {
    return {
      queryStartRoadName: '',
      queryEndRoadName: '',
      queryTimeslot: '',
      pageNum: 1,
      pageSize: 10,
      isLoading: true,
      loadingText: '',
      multipleSelectedPosition: []
    }
  },
  computed: {
    ...mapState('road', ['roadSeekingTripList'])
  },
  created() {
    this.handleGetRoadSeekingTripList();
  },
  methods: {
    ...mapActions('road', ['getRoadSeekingTripList']),
    handleSetLineChartData(type) {
      this.$emit('handleSetLineChartData', type)
    },
    handleGetRoadSeekingTripList() {
      this.loadingText = '正在加载寻客路段数据';
      this.isLoading = true;
      this.getRoadSeekingTripList({
        pagination: {
          pageNum: this.pageNum,
          pageSize: this.pageSize
        }
      })
        .then(() => {
          this.isLoading = false
        })
        .catch(err => {
          this.isLoading = false
          if (typeof err === 'string' && err !== 'cancel') {
            this.$message.error(err)
          }
        });
    },
    handleSelectionChange(val) {
      this.multipleSelectedPosition = val;
    },
    handleSizeChange(val) {
      this.pageSize = val;
      this.handleGetRoadSeekingTripList();
    },

    handleCurrentChange(val) {
      this.pageNum = val;
      this.handleGetRoadSeekingTripList();
    },

    showRoadDetail(row) {
      this.$message.info('功能正在开发中....');
    },

    showMultiRoadDetail() {
      this.$message.info('功能正在开发中....');
    },

    showRoadPanorama(row) {
      this.$router.push({
        name: 'RoadMapPanorama',
        params: {
          roadLon: row.roadLon,
          roadLat: row.roadLat
        }
      });
    },

    filter() {
      this.loadingText = '正在加载寻客路段数据'
      this.isLoading = true
      this.getRoadSeekingTripList({
        pagination: {
          pageNum: this.pageNum,
          pageSize: this.pageSize
        },
        timeslot: this.queryTimeslot,
        startRoadInfo: {
          roadName: this.queryStartRoadName
        },
        endRoadInfo: {
          roadName: this.queryEndRoadName
        }
      })
        .then(() => {
          this.isLoading = false
        })
        .catch(err => {
          this.isLoading = false
          if (typeof err === 'string' && err !== 'cancel') {
            this.$message.error(err)
          }
        });
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" >
#road-list {
  .road-list {
    margin: 50px;
    align: "center";
  }
  .input-with-select {
    width: 180px;
    margin-right:15px
  }
  .align-center {
    text-align: center;
  }
}
</style>
