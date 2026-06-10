import { useState, useEffect } from 'react'
import Sidebar from './components/Sidebar'
import Topbar from './components/Topbar'
import Login from './components/Login'
import GenelBakis from './pages/GenelBakis'
import Trafik from './pages/Trafik'
import Senaryo from './pages/Senaryo'
import Enerji from './pages/Enerji'
import Cevre from './pages/Cevre'
import AcilDurum from './pages/AcilDurum'
import Raporlar from './pages/Raporlar'
import ApiKontrol from './pages/ApiKontrol'
import './App.css'

const PAGES = {
  genel: GenelBakis,
  trafik: Trafik,
  senaryo: Senaryo,
  enerji: Enerji,
  cevre: Cevre,
  acil: AcilDurum,
  rapor: Raporlar,
  api: ApiKontrol,
}

export default function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(false)
  const [activePage, setActivePage] = useState('genel')
  const [zaman, setZaman] = useState('Bugün')
  const [kavsak, setKavsak] = useState('all')

  useEffect(() => {
    const token = localStorage.getItem('token')
    const role = localStorage.getItem('role')
    if (token && role === 'municipality_admin') {
      setIsLoggedIn(true)
    } else {
      localStorage.clear()
    }
  }, [])

  const handleLoginSuccess = (role) => {
    if (role === 'municipality_admin') {
      setIsLoggedIn(true)
    } else {
      alert('Yalnızca belediye yöneticileri yönetim paneline erişebilir.')
      localStorage.clear()
    }
  }

  const handleLogout = () => {
    localStorage.clear()
    setIsLoggedIn(false)
  }

  if (!isLoggedIn) {
    return <Login onLoginSuccess={handleLoginSuccess} />
  }

  const ActiveComponent = PAGES[activePage]

  return (
    <div className="app">
      <Sidebar 
        activePage={activePage} 
        setActivePage={setActivePage} 
        onLogout={handleLogout} 
      />
      <div className="main">
        <Topbar
          zaman={zaman} setZaman={setZaman}
          kavsak={kavsak} setKavsak={setKavsak}
          activePage={activePage}
        />
        <div className="content">
          <ActiveComponent zaman={zaman} kavsak={kavsak} />
        </div>
      </div>
    </div>
  )
}
