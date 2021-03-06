/** When your routing table is too long, you can split it into small modules**/

import Layout from '@/views/layout/Layout'

const roadRouter = {
  path: '/road',
  component: Layout,
  redirect: '/road/complex-road',
  name: 'Road',
  meta: {
    title: 'Road',
    icon: 'guide'
  },
  children: [
    {
      path: 'road-overview',
      component: () => import('@/views/road/overview'),
      name: 'RoadOverview',
      meta: { title: 'RoadOverview' }
    },
    {
      path: 'road-list',
      component: () => import('@/views/road/index'),
      name: 'RoadList',
      meta: { title: 'RoadList' }
    },
    {
      path: 'road-seeking-trip-list',
      component: () => import('@/views/road/seekingtrip'),
      name: 'RoadSeekingTripList',
      meta: { title: 'RoadSeekingTripList' }
    },
    {
      path: 'road-driving-trip-list',
      component: () => import('@/views/road/drivingtrip'),
      name: 'RoadDrivingTripList',
      meta: { title: 'RoadDrivingTripList' }
    },
    {
      path: 'road-map-detail',
      component: () => import('@/views/road/mapdetail'),
      name: 'RoadMapDetail',
      meta: { title: 'RoadMapDetail' },
      hidden: true
    },
    {
      path: 'road-map-panorama',
      component: () => import('@/views/road/mappanorama'),
      name: 'RoadMapPanorama',
      meta: { title: 'RoadMapPanorama' },
      hidden: true
    }
  ]
}
export default roadRouter
