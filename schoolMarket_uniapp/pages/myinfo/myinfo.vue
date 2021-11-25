<template>
	<view>
		<view class="user-section">
			<image class="bg" src="/static/user-bg.jpg"></image>
			<view class="topPart">
				<view class="portrait-box">
					<image class="portrait" :src="userInfo.portrait || '/static/missing-face.png'"></image>
					<text class="name">{{userInfo.username}}</text>
				</view>
			</view>
		</view>
		<uni-list class="user_detail">
			<uni-list-item title="昵称">
				<template slot="footer">
					<input :placeholder="userInfo.username" v-model="userInfo.username" />
				</template>
			</uni-list-item>
			<uni-list-item title="学院">
				<template slot="footer">
					<input :placeholder="userInfo.faculty" v-model="userInfo.faculty" />
				</template>
			</uni-list-item>
			<uni-list-item title="地址">
				<template slot="footer">
					<input :placeholder="userInfo.address" v-model="userInfo.address" />
				</template>
			</uni-list-item>
			<uni-list-item title="QQ号">
				<template slot="footer">
					<input :placeholder="userInfo.qqnumber" v-model="userInfo.qqnumber" />
				</template>
			</uni-list-item>
			<uni-list-item title="微信号">
				<template slot="footer">
					<input :placeholder="userInfo.vxnumber" v-model="userInfo.vxnumber" />
				</template>
			</uni-list-item>
		</uni-list>
		<view class="subPart">
			<button class="subBtn" form-type="submit" hover-class="subBtnPrs" @click="updateUser">确认修改</button>
		</view>
	</view>
</template>

<script>
	// import {  
	//     mapState,  
	//     mapMutations  
	// } from 'vuex';  
	export default {

		data() {
			return {
				userInfo: {
					username: '',
					faculty: '',
					address: '',
					qqnumber: '',
					vxnumber: ''
				},
				username: '',
				userId: -1,
			};
		},
		// computed:{
		// 	...mapState(['userInfo']),
		// },
		onLoad(options) {
			let id = options.id;
			this.userId = id;
			this.getUserInfo(id)
		},
		methods: {
			//getuserbyownerid
			update(num, content) {
				console.log(content);
			},
			getUserInfo(id) {
				var that = this;
				uni.request({
					url: 'http://localhost:8080/user/getuserbyid?',
					method: 'GET',
					data: {
						id: id,
					},
					success: function(res) {
						console.log(res.data);
						that.username = res.data.username;
						that.userInfo.username = res.data.username;
						that.userInfo.faculty = res.data.faculty;
						that.userInfo.address = res.data.address;
						that.userInfo.qqnumber = res.data.qqnumber;
						that.userInfo.vxnumber = res.data.vxnumber;
					},
					fail: function(res) {
						console.log(res);
					},
				});
			},
			updateUser() {
				var that = this;
				uni.request({
					url: 'http://localhost:8080/user/updateuser',
					method: 'POST',
					data: {
						id: Number(this.userId),
						username: that.userInfo.username,
						faculty: that.userInfo.faculty,
						address: that.userInfo.address,
						qqnumber: that.userInfo.qqnumber,
						vxnumber: that.userInfo.vxnumber,
					},
					header: {
					    'Content-Type': 'application/x-www-form-urlencoded'
					},
					success: function(res) {
						console.log(res.data);
						uni.showToast({
							title: '修改成功！',
							icon: 'success'
						})
						setTimeout(function() {
							uni.navigateBack()
						}, 2000);
					},
					fail: function(res) {
						console.log(res)
					}
				})
			}
		}
	}
</script>

<style lang="scss">
	page {
		background: $page-color-base;
	}

	input {
		display: block;
		float: right;
		text-align: right;
	}

	.user-section {
		display: flex;
		align-items: center;
		justify-content: center;
		height: 460upx;
		padding: 40upx 30upx 0;
		position: relative;
		text-align: center;

		.bg {
			position: absolute;
			left: 0;
			top: 0;
			width: 100%;
			height: 100%;
			filter: blur(1px);
			opacity: .7;
		}

		.portrait-box {
			width: 200upx;
			height: 200upx;
			border: 6upx solid #fff;
			border-radius: 50%;
			position: relative;
			z-index: 2;
		}

		.portrait {
			position: relative;
			width: 100%;
			height: 100%;
			border-radius: 50%;
		}

		.yticon {
			position: absolute;
			line-height: 1;
			z-index: 5;
			font-size: 48upx;
			color: #fff;
			padding: 4upx 6upx;
			border-radius: 6upx;
			background: rgba(0, 0, 0, .4);
		}

		.pt-upload-btn {
			right: 0;
			bottom: 10upx;
		}

		.bg-upload-btn {
			right: 20upx;
			bottom: 16upx;
		}

		.name {
			width: 200upx;
			text-align: center;
		}

		.title {
			display: inline;
		}
	}
	.subPart {
		position: fixed;
		bottom: 110upx;
		left: 0;
		right: 0;
		padding: 30upx 40upx;
		//background-color: #;
	
		.subBtn {
			background: linear-gradient(to right, #ffac30, #fa436a, #ffac30);
			border-radius: 100px;
			font-size: 25upx;
			font-weight: bold;
			color: #ffffff;
			box-shadow: 1px 2px 5px rgba(219, 63, 96, 0.4);
		}
	
		.subBtnPrs {
			background: #ea3e63;
			top: 3upx;
			left: 3upx;
			box-shadow: 1px 2px 5px rgba(219, 63, 96, 0.4);
		}
	}
</style>
