const NAV_ITEMS = [
  { id: 'genel', label: 'Genel Bakış', icon: '📊' },
  { id: 'trafik', label: 'Trafik Yönetimi', icon: '🚦' },
  { id: 'senaryo', label: 'Senaryo Simülatörü', icon: '▶️' },
  { id: 'enerji', label: 'Enerji Yönetimi', icon: '⚡' },
  { id: 'cevre', label: 'Çevre İzleme', icon: '🌿' },
  { id: 'acil', label: 'Acil Durum', icon: '🚑', badge: 1 },
  { id: 'rapor', label: 'Raporlar & Analiz', icon: '📈' },
]

export default function Sidebar({ activePage, setActivePage }) {
  return (
    <div className="sidebar">
      <div className="sidebar-header">
        <div className="sidebar-logo">
          <div className="logo-icon">🏛️</div>
          <div>
            <div className="logo-title">Elazığ Belediyesi</div>
            <div className="logo-sub">Akıllı Şehir Yönetim Sistemi</div>
          </div>
        </div>
      </div>
      <div className="sidebar-nav">
        <div className="nav-section">Ana Modüller</div>
        {NAV_ITEMS.slice(0, 6).map(item => (
          <button
            key={item.id}
            className={`nav-item ${activePage === item.id ? 'active' : ''}`}
            onClick={() => setActivePage(item.id)}
          >
            <span>{item.icon}</span>
            <span>{item.label}</span>
            {item.badge && <span className="nav-badge">{item.badge}</span>}
          </button>
        ))}
        <div className="nav-section">Raporlama</div>
        {NAV_ITEMS.slice(6).map(item => (
          <button
            key={item.id}
            className={`nav-item ${activePage === item.id ? 'active' : ''}`}
            onClick={() => setActivePage(item.id)}
          >
            <span>{item.icon}</span>
            <span>{item.label}</span>
          </button>
        ))}
      </div>
      <div className="sidebar-footer">
        <div className="user-box">
          <div className="user-avatar">YE</div>
          <div>
            <div className="user-name">Yetkili Operatör</div>
            <div className="user-role">Belediye Yöneticisi</div>
          </div>
        </div>
      </div>
    </div>
  )
}
