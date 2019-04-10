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
        :title="dialogTitle">
        <el-form
          v-loading="isAddLoading"
          ref="roadForm"
          :element-loading-text="operateLoadingText"
          :rules="rules"
          :model="roadForm"
          element-loading-background="rgba(0, 0, 0, 0.5)"
          label-width="100px" >
          <el-form-item label="路段名：" prop="roadName">
            <el-input v-model="roadForm.roadName" class="input-with-select" />
          </el-form-item>
          <el-form-item label="路段经度：" prop="roadLon">
            <el-input v-model.number="roadForm.roadLon" class="input-with-select" />
          </el-form-item>
          <el-form-item label="路段纬度：" prop="roadLat">
            <el-input v-model.number="roadForm.roadLat" class="input-with-select" />
          </el-form-item>
          <el-form-item label="路段长度：" prop="roadLength">
            <el-input v-model.number="roadForm.roadLength" class="input-with-select" />
          </el-form-item>
          <el-form-item label="路段时间：" prop="roadTime">
            <el-input v-model.number="roadForm.roadTime" class="input-with-select" />
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="confirmOperateRoadData('roadForm')">确 定</el-button>
        </span>
      </el-dialog>
      <div style="margin-top:15px;" />
      <template>
        <el-table
          v-loading="isLoading"
          :data="roadList"
          :element-loading-text="loadingText"
          :default-sort="{prop: 'roadId', order: 'descending'}"
          element-loading-background="rgba(0, 0, 0, 0.5)"
          stripe
          border
          highlight-current-row
          fit
          row-class-name="align-center"
          header-cell-class-name="align-center"
          style="width: 80%"
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
          <el-table-column label="路段经度" width="120">
            <template slot-scope="scope">
              <span>{{ scope.row.roadLon }}</span>
            </template>
          </el-table-column>
          <el-table-column label="路段纬度" width="120">
            <template slot-scope="scope">
              <span>{{ scope.row.roadLat }}</span>
            </template>
          </el-table-column>
          <el-table-column label="路段长度(米)" width="120">
            <template slot-scope="scope">
              <span>{{ scope.row.roadLength }}</span>
            </template>
          </el-table-column>
          <el-table-column label="路段时间(分钟)" width="120">
            <template slot-scope="scope">
              <span>{{ scope.row.roadTime }}</span>
            </template>
          </el-table-column>
          <el-table-column fixed = "right" label = "操作" width = "300">
            <template slot-scope="scope">
              <el-button size="mini" type="primary" @click="showRoadDetail(scope.row)">
                定位
              </el-button>
              <el-button size="mini" type="success" @click="showRoadPanorama(scope.row)">
                全景
              </el-button>
              <el-button size="mini" type="info" @click="updateRoadData('roadForm', scope.row)">
                更新
              </el-button>
              <el-button size="mini" type="danger" @click="deleteRoadData(scope.row)">
                删除
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </template>
      <el-pagination
        :total="roadList && roadList.length > 0 ? roadList[0].pagination.total : 0"
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
      isAddLoading: '',
      loadingText: '',
      operateLoadingText: '',
      multipleSelectedPosition: [],
      dialogVisible: false,
      dialogTitle: '',
      roadOperation: '',
      roadForm: {
        roadId: '',
        roadName: '',
        roadLon: '',
        roadLat: '',
        roadLength: '',
        roadTime: ''
      },
      rules: {
        roadName: [
          { required: true, message: '请输入路段名', trigger: 'blur' },
          { min: 3, max: 15, message: '路段名长度在3到15之间', trigger: 'blur' }
        ],
        roadLon: [
          { required: true, message: '请输入路段经度', trigger: 'blur' },
          { type: 'number', message: '路段经度必须为数字值', trigger: 'blur' }
        ],
        roadLat: [
          { required: true, message: '请输入路段纬度', trigger: 'blur' },
          { type: 'number', message: '路段纬度必须为数字值', trigger: 'blur' }
        ],
        roadLength: [
          { required: true, message: '请输入路段长度', trigger: 'blur' },
          { type: 'number', message: '路段长度必须为数字值', trigger: 'blur' }
        ],
        roadTime: [
          { required: true, message: '请输入路段时间', trigger: 'blur' },
          { type: 'number', message: '路段时间必须为数字值', trigger: 'blur' }
        ]
      }
    }
  },
  computed: {
    ...mapState('road', ['roadList'])
  },
  created() {
    this.loadingText = '正在加载路段数据';
    this.isLoading = true;
    this.getRoadList({
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
  methods: {
    ...mapActions('road', ['getRoadList', 'addRoad', 'deleteRoad', 'updateRoad']),
    handleSetLineChartData(type) {
      this.$emit('handleSetLineChartData', type)
    },
    handleSelectionChange(val) {
      this.multipleSelectedPosition = val;
    },
    handleGetRoadList() {
      this.loadingText = '正在加载路段数据'
      this.isLoading = true
      this.getRoadList({
        pagination: {
          pageNum: this.pageNum,
          pageSize: this.pageSize
        },
        roadName: this.queryRoadName
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
    handleSizeChange(val) {
      this.pageSize = val;
      this.handleGetRoadList();
    },

    handleCurrentChange(val) {
      this.pageNum = val;
      this.handleGetRoadList();
    },

    // only show dialogVisible
    addRoadData() {
      this.dialogTitle = '添加路段信息';
      this.operateLoadingText = '正在添加路段信息';
      this.roadOperation = 'add';
      this.dialogVisible = true;
    },

    updateRoadData(formName, row) {
      console.log('formName:', formName);
      this.dialogTitle = '更新路段信息';
      this.operateLoadingText = '正在更新路段信息';
      this.roadOperation = 'update';
      this.dialogVisible = true;
      this.roadForm = {
        roadId: row.roadId,
        roadName: row.roadName,
        roadLon: row.roadLon,
        roadLat: row.roadLat,
        roadLength: row.roadLength,
        roadTime: row.roadTime
      };
    },

    confirmOperateRoadData(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.isAddLoading = true;
          if (this.roadOperation === 'add') {
            this.addRoad(this.roadForm).then(() => {
              this.isAddLoading = false;
              this.dialogVisible = false;
              this.$message.success('路段信息添加成功');
              this.filter();
            }).catch(err => {
              this.isAddLoading = false;
              this.dialogVisible = false;
              if (typeof err === 'string' && err !== 'cancel') {
                this.$message.error(err);
              }
            })
          } else {
            this.updateRoad(this.roadForm).then(() => {
              this.isAddLoading = false;
              this.dialogVisible = false;
              this.$message.success('路段信息更新成功');
              // this.filter();
            }).catch(err => {
              this.isAddLoading = false;
              this.dialogVisible = false;
              if (typeof err === 'string' && err !== 'cancel') {
                this.$message.error(err);
              }
            })
          }
        } else {
          return false;
        }
      });
    },

    deleteRoadData(row) {
      this.$confirm('此操作将永久删除路段 ' + row.roadName + ' 是否继续？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.loadingText = '正在删除路段信息';
        this.isLoading = true;
        return this.deleteRoad({
          roadId: row.roadId
        }).then(() => {
          this.isLoading = false;
          this.$message.success('路段信息删除成功');
          this.filter();
        }).catch(err => {
          this.isLoading = false;
          if (typeof err === 'string' && err !== 'cancel') {
            this.$message.error(err);
          }
        })
      })
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

    // 路段信息过滤
    filter() {
      this.handleGetRoadList();
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
