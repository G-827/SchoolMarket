<template>
  <div>
    <el-dialog
      title="警告"
      :visible.sync="InvalidInputDialogVisible"
      width="30%"
      :show-close="false"
    >
      <span>输入无效</span>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="InvalidInputDialogVisible = false">确 定</el-button>
      </span>
    </el-dialog>
    <el-dialog
      title="修改"
      :visible.sync="UpdatedialogVisible"
      width="30%"
    >
      <el-form :model="upDateData" :inline="true">
        <el-form-item label="ID">
          <el-input v-model="upDateData.id" :disabled="true" size="small" />
        </el-form-item>
        <el-form-item label="时间">
          <el-input v-model="upDateData.name" size="small" placeholder="菜名" />
        </el-form-item>
        <el-form-item label="买家">
          <el-input v-model="upDateData.img_url" size="small" placeholder="图片链接" />
        </el-form-item>
        <el-form-item label="卖家">
          <el-input v-model="upDateData.img_url" size="small" placeholder="图片链接" />
        </el-form-item>
        <el-form-item label="商品名">
          <el-input v-model="upDateData.img_url" size="small" placeholder="图片链接" />
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="commitEdit">确 定</el-button>
      </span>
    </el-dialog>
    <el-divider />
    <el-row>
      <el-col :span="10"><br></el-col>
      <el-col :span="14">
        <el-form :inline="true" :model="addData">
          <el-form-item label="菜名">
            <el-input v-model="addData.name" size="small" placeholder="菜名" />
          </el-form-item>
          <el-form-item label="食堂">
            <el-select v-model="addData.canteen" size="small" placeholder="食堂">
              <el-option v-for="(item) in canteens" :key="item.id" :label="item.name" :value="item.name" />
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button type="success" size="mini" @click="AddOrder">添加</el-button>
            <el-button type="primary" size="mini" @click="SearchOrder">搜索</el-button>
            <el-button type="plain" size="mini" @click="getAllOrder">刷新</el-button>
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>
    <el-table
      id="TableTop"
      :data="tableData.slice((currentPage-1)*pageSize,currentPage*pageSize)"
      style="width: 100%"
      @sort-change="SortById"
    >
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-form label-position="left" inline class="demo-table-expand">
            <el-form-item label="ID">
              <span>{{ props.row.id }}</span>
            </el-form-item>
            <el-form-item label="时间">
              <span>{{ props.row.datetime }}</span>
            </el-form-item>
            <el-form-item label="买家">
              <span>{{ props.row.buyername }}</span>
            </el-form-item>
            <el-form-item label="卖家">
              <span>{{ props.row.sellername }}</span>
            </el-form-item>
            <el-form-item label="商品名">
              <span>{{ props.row.productname }}</span>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>
      <el-table-column
        align="center"
        prop="id"
        label="编号"
        sortable="custom"
      >
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.orderid }}</span>
        </template>
      </el-table-column>
      <el-table-column
        align="center"
        label="时间"
      >
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.datetime }}</span>
        </template>
      </el-table-column>
      <el-table-column
        align="center"
        label="买家"
      >
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.buyername }}</span>
        </template>
      </el-table-column>
      <el-table-column
        align="center"
        label="卖家"
      >
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.sellername }}</span>
        </template>
      </el-table-column>
      <el-table-column
        align="center"
        label="商品名"
      >
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.productname }}</span>
        </template>
      </el-table-column>
      <el-table-column
        align="center"
        label="操作"
      >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="warning"
            @click="handleEdit(scope.row.id, scope.row)"
          >编辑</el-button>
          <el-button
            size="mini"
            type="danger"
            @click="handleDelete(scope.row.id, scope.row)"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div style="text-align: center;margin-top: 30px;">
      <el-pagination
        :current-page="currentPage"
        :page-size="pageSize"
        :pager-count="5"
        layout="total, pager"
        :total="tableData.length"
        @current-change="handleCurrentChange"
      />
    </div>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'Index',
  data() {
    return {
      currentPage: 1,
      pageSize: 6,
      tableData: [],
      allData: [],
      canteens: [],
      InvalidInputDialogVisible: false,
      UpdatedialogVisible: false,
      addData: {
        'id': 0,
        'name': '',
        'canteen': '',
        'img_url': ''
      },
      upDateData: {
        'id': -1,
        'name': '',
        'canteen': '',
        'img_url': ''
      },
      sortState: 0
    }
  },
  created() {
    var that = this
    that.getAllOrder()
  },
  methods: {
    getAllOrder() {
      var that = this
      axios.get(this.$store.state.orderHeadUrl + 'getallorder').then(function(response) {
        that.allData = response.data
        that.tableData = response.data
        console.log(that.tableData)
      }).catch(function(error) {
        console.log(error)
      })
    },
    deleteOrderById(id) {
      axios.delete(this.$store.state.orderHeadUrl + 'deleteorderbyid/' + id).then(function(response) {
        console.log(response.data)
      }).catch(function(error) {
        console.log(error)
      })
    },
    addOrder() {
      var that = this
      axios({
        method: 'post',
        url: this.$store.state.orderHeadUrl + 'addorder',
        data: {
          'id': that.addData.id,
          'name': that.addData.name,
          'canteen': that.addData.canteen,
          'img_url': that.addData.img_url
        },
        transformRequest: [
          function(data) {
            let ret = ''
            for (const it in data) {
              ret += encodeURIComponent(it) + '=' + encodeURIComponent(data[it]) + '&'
            }
            ret = ret.substring(0, ret.lastIndexOf('&'))
            return ret
          }
        ],
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        }
      }).then(function(response) {
        that.getAllOrder()
      })
    },
    updateOrder() {
      var that = this
      axios({
        method: 'post',
        url: this.$store.state.orderHeadUrl + 'updateOrder',
        data: {
          'id': that.upDateData.id,
          'name': that.upDateData.name,
          'canteen': that.upDateData.canteen,
          'img_url': that.upDateData.img_url
        },
        transformRequest: [
          function(data) {
            let ret = ''
            for (const it in data) {
              ret += encodeURIComponent(it) + '=' + encodeURIComponent(data[it]) + '&'
            }
            ret = ret.substring(0, ret.lastIndexOf('&'))
            return ret
          }
        ],
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        }
      }).then(function(response) {
        that.getAllOrder()
      })
    },
    handleCurrentChange(currentPage) {
      this.currentPage = currentPage
      location.href = '#TableTop'
    },
    IndexOfId(id) {
      var index = -1
      for (var i = 0; i < this.tableData.length; i++) {
        if (this.tableData[i].id === id) {
          index = i
        }
      }
      return index
    },
    handleDelete(id, row) {
      var index = this.IndexOfId(id)
      console.log('index:' + index)
      this.tableData.splice(index, 1)
      if (this.tableData.length % this.pageSize === 0) {
        this.currentPage--
      }
      console.log('id:' + id)
      this.deleteOrderById(id)
    },
    handleEdit(id, row) {
      this.UpdatedialogVisible = true
      var index = this.IndexOfId(id)
      this.upDateData = JSON.parse(JSON.stringify(this.tableData[index]))
    },
    commitEdit() {
      this.updateOrder()
      this.UpdatedialogVisible = false
    },
    AddOrder() {
      var that = this
      if (that.addData.name === '' || that.addData.canteen === '') {
        that.InvalidInputDialogVisible = true
        return
      }
      console.log(that.addData)
      that.addOrder()
    },
    SearchOrder() {
      var that = this
      that.currentPage = 1
      that.tableData = that.allData.filter(item => {
        return item.name.includes(that.addData.name) && item.canteen.includes((that.addData.canteen))
      })
      console.log(that.tableData)
    },
    SortById() {
      this.sortState = (this.sortState + 1) % 3
      if (this.sortState === 1) {
        this.tableData.sort((a, b) => b.orderid - a.orderid)
      } else if (this.sortState === 2) {
        this.tableData.reverse()
      }
      console.log(this.sortState)
    }
  }
}
</script>

<style scoped>
.demo-table-expand {
  font-size: 0;
}
.demo-table-expand label {
  width: 90px;
  color: #99a9bf;
}
.demo-table-expand .el-form-item {
  margin-right: 0;
  margin-bottom: 0;
  width: 50%;
}
</style>
