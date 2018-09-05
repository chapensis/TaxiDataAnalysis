<template>
  <div id="road-list">
    <div class = "road-list">
      <!--查询条件-->
      <template>
        路段名：
        <el-input v-model="queryRoadName"
                  placeholder="请输入查询路段名"
                  prefix-icon="el-icon-search"
                  class="input-with-select"
                  size="mini" />
        <el-button type="primary"
                   size="mini"
                   @click="filter">搜索</el-button>
        <el-button type="success"
                   size="mini"
                   @click="addRoadData">添加</el-button>
        <el-button type="danger"
                   size="mini"
                   @click="showMultiRoadDetail">批量定位</el-button>
      </template>
      <el-dialog
        :visible.sync="dialogVisible"
        :before-close="handleClose"
        title="添加新路段">
        <el-form ref="roadForm" :model="roadForm" label-width="80px">
          <el-form-item label="路段名">
            <el-input v-model="roadForm.roadName" />
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
        </span>
      </el-dialog>
      <div style="margin-top:15px;" />
      <template>
        <el-table
          v-loading="isLoading"
          :data="roadList"
          :element-loading-text="loadingText"
          :default-sort="{prop: 'roadId', order: 'descending'}"
          stripe
          border
          highlight-current-row
          fit
          row-class-name="align-center"
          header-cell-class-name="align-center"
          style="width: 100%"
          @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="55" />
          <el-table-column label="路段Id" width="80">
            <template slot-scope="scope">
              <span>{{ scope.row.roadId }}</span>
            </template>
          </el-table-column>
          <el-table-column label="路段名" width="120">
            <template slot-scope="scope">
              <span>{{ scope.row.roadName }}</span>
            </template>
          </el-table-column>
          <el-table-column label="路段经度">
            <template slot-scope="scope">
              <span>{{ scope.row.roadLon }}</span>
            </template>
          </el-table-column>
          <el-table-column label="路段纬度">
            <template slot-scope="scope">
              <span>{{ scope.row.roadLat }}</span>
            </template>
          </el-table-column>
          <el-table-column label="路段长度(米)" width="120">
            <template slot-scope="scope">
              <span>{{ scope.row.roadLength }}</span>
            </template>
          </el-table-column>
          <el-table-column label="路段时间(分钟)">
            <template slot-scope="scope">
              <span>{{ scope.row.roadTime }}</span>
            </template>
          </el-table-column>
          <el-table-column fixed = "right" label = "操作" width = "240">
            <template slot-scope="scope">
              <el-button size="mini" type="primary" @click="showRoadDetail(scope.row)">
                定位
              </el-button>
              <el-button size="mini" type="success" @click="showRoadPanorama(scope.row)">
                全景
              </el-button>
              <el-button size="mini" type="danger" @click="deleteRoadData(scope.row)">
                删除
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </template>
      <el-pagination
        :total="roadListNum"
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
      queryRoadName: '',
      pageNum: 1,
      pageSize: 10,
      isLoading: '',
      loadingText: '',
      multipleSelectedPosition: [],
      dialogVisible: false,
      roadForm: {
        roadName: ''
      }
    }
  },
  computed: {
    ...mapState('road', ['roadList', 'roadListNum'])
  },
  created() {
    this.loadingText = '正在加载路段数据';
    this.isLoading = true;
    Promise.all([
      this.getRoadList({
        pagination: {
          pageNum: this.pageNum,
          pageSize: this.pageSize
        }
      }),
      this.getTotalRoadNum({
        roadName: this.queryRoadName
      })
    ])
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
  methods: {
    ...mapActions('road', ['getRoadList', 'getTotalRoadNum']),
    handleSetLineChartData(type) {
      this.$emit('handleSetLineChartData', type)
    },
    handleSelectionChange(val) {
      this.multipleSelectedPosition = val;
    },
    handleSizeChange(val) {
      this.loadingText = '正在加载路段数据'
      this.isLoading = true
      this.pageSize = val;
      Promise.all([
        this.getRoadList({
          pagination: {
            pageNum: this.pageNum,
            pageSize: this.pageSize
          },
          roadName: this.queryRoadName
        }),
        this.getTotalRoadNum({
          roadName: this.queryRoadName
        })
      ])
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

    handleCurrentChange(val) {
      this.loadingText = '正在加载路段数据';
      this.isLoading = true;
      this.pageNum = val;
      Promise.all([
        this.getRoadList({
          pagination: {
            pageNum: this.pageNum,
            pageSize: this.pageSize
          },
          roadName: this.queryRoadName
        }),
        this.getTotalRoadNum({
          roadName: this.queryRoadName
        })
      ])
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

    addRoadData() {
      this.dialogVisible = true;
    },

    deleteRoadData(row) {
      this.$message.info('正在开发中...')
    },

    showRoadDetail(row) {
      this.$router.push({
        name: 'RoadMapDetail',
        params: [{
          roadLon: row.roadLon,
          roadLat: row.roadLat
        }]
      });
    },

    showMultiRoadDetail() {
      if (this.multipleSelectedPosition.length === 0) {
        this.$message.warning('请先选择要定位的路段');
        return;
      }
      this.$router.push({
        name: 'RoadMapDetail',
        params: this.multipleSelectedPosition
      });
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
      this.loadingText = '正在加载路段数据'
      this.isLoading = true
      Promise.all([
        this.getRoadList({
          pagination: {
            pageNum: this.pageNum,
            pageSize: this.pageSize
          },
          roadName: this.queryRoadName
        }),
        this.getTotalRoadNum({
          roadName: this.queryRoadName
        })
      ])
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

<style rel="stylesheet/scss" lang="scss">
#road-list {
  .road-list {
    margin: 50px;
    align: "center";
  }
  .input-with-select {
    width: 150px;
    margin-right:15px
  }
  .align-center {
    text-align: center;
  }
}
</style>
