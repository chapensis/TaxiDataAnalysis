/** When your routing table is too long, you can split it into small modules**/

import Layout from '@/views/layout/Layout'

const businessRouter = {
  path: '/business',
  component: Layout,
  redirect: '/business/complex-business',
  name: 'Business',
  meta: {
    title: 'Business',
    icon: 'money'
  },
  children: [
    {
      path: 'business-overview',
      component: () => import('@/views/business/overview'),
      name: 'BusinessOverview',
      meta: { title: 'BusinessOverview' }
    },
    {
      path: 'business-list',
      component: () => import('@/views/business/index'),
      name: 'BusinessList',
      meta: { title: 'BusinessList' }
    },
    {
      path: 'business-map-detail',
      component: () => import('@/views/business/mapdetail'),
      name: 'BusinessMapDetail',
      meta: { title: 'BusinessMapDetail' },
      hidden: true
    }
  ]
}
export default businessRouter
