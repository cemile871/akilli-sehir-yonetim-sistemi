import { AreaChart, Area, BarChart, Bar, XAxis, YAxis, CartesianGrid, Tooltip, ResponsiveContainer } from 'recharts'

const trafikData = [
  { saat: '00', deger: 120 }, { saat: '02', deger: 90 }, { saat: '04', deger: 70 },
  { saat: '06', deger: 60 }, { saat: '08', deger: 480 }, { saat: '10', deger: 380 },
  { saat: '12', deger: 320 }, { saat: '14', deger: 340 }, { saat: '16', deger: 290 },
  { saat: '18', deger: 520 }, { saat: '20', deger: 280 }, { saat: '22', deger: 160 },
]

const enerjiData = [
  { kavsak: 'Çarşı', deger: 1320 },
  { kavsak: 'Tofaş', deger: 660 },
  { kavsak: 'İzzet P.', deger: 1100 },
  { kavsak: 'Palu', deger: 440 },
  { kavsak: 'Üniversite', deger: 880 },
]

const METRIKLER = [
  { label: 'Ort. Bekleme', value: '28 sn', sub: '✓ Hedef <30 sn', color: '#639922', topColor: '#639922' },
  { label: 'Enerji Tüketimi', value: '4.820 kWh', sub: '↑ +%8 artış', color: '#854F0B', topColor: '#EF9F27' },
  { label: 'Ort. AQI', value: '52.4', sub: '⚠ Orta seviye', color: '#854F0B', topColor: '#EF9F27' },
  { label: 'Müdahale Süresi', value: '3.5 dk', sub: '✓ Hedef <6 dk', color: '#639922', topColor: '#639922' },
  { label: 'Enerji Tasarrufu', value: '-%20', sub: '✓ Hedef karşılandı', color: '#639922', topColor: '#639922' },
  { label: 'Sistem Uptime', value: '%99.9', sub: '✓ Karşılandı', color: '#185FA5', topColor: '#378ADD' },
]

const KPILER = [
  { label: 'Bekleme süresi', value: '28 sn', target: 'Hedef: <30 sn — %93', percent: 93, color: '#639922' },
  { label: 'Müdahale süresi', value: '3.5 dk', target: 'Hedef: <6 dk — %100', percent: 100, color: '#639922' },
  { label: 'Enerji tasarrufu', value: '-%20', target: 'Hedef: -%20 — %100', percent: 100, color: '#639922' },
  { label: 'Hava kalitesi (AQI)', value: '52.4', target: 'Hedef: <30 — %57', percent: 57, color: '#EF9F27' },
  { label: 'Geçiş kapasitesi', value: '1.100', target: 'Hedef: >1.000 araç/saat — %100', percent: 100, color: '#639922' },
]

export default function GenelBakis() {
  return (
    <div>
      <div className="alert alert-r">
        <span>🚑</span>
        <span><strong>Aktif Acil Durum:</strong> Ambulans İzzet Paşa → Üniversite güzergahında. Yeşil dalga aktif. Müdahale süresi: 3.5 dk</span>
      </div>

      <div className="grid5" style={{ gridTemplateColumns: 'repeat(6, 1fr)' }}>
        {METRIKLER.map((m, i) => (
          <div key={i} className="mc" style={{ borderTopColor: m.topColor }}>
            <div className="mc-label">{m.label}</div>
            <div className="mc-val" style={{ color: m.color }}>{m.value}</div>
            <div className="mc-sub" style={{ color: m.color }}>{m.sub}</div>
          </div>
        ))}
      </div>

      <div className="sec-title">📊 KPI Performansı</div>
      <div className="grid5" style={{ gridTemplateColumns: 'repeat(5, 1fr)' }}>
        {KPILER.map((k, i) => (
          <div key={i} className="kpi">
            <div className="kpi-label">{k.label}</div>
            <div className="kpi-val" style={{ color: k.color }}>{k.value}</div>
            <div className="kpi-target">{k.target}</div>
            <div className="kpi-bar-wrap">
              <div className="kpi-bar" style={{ width: `${k.percent}%`, background: k.color }} />
            </div>
          </div>
        ))}
      </div>

      <div className="grid2">
        <div className="card">
          <div className="card-title">Saatlik trafik yoğunluğu</div>
          <div className="card-sub">Çarşı Kavşağı — araç/saat</div>
          <ResponsiveContainer width="100%" height={220}>
            <AreaChart data={trafikData}>
              <CartesianGrid strokeDasharray="3 3" stroke="#f1f5f9" />
              <XAxis dataKey="saat" tick={{ fontSize: 11 }} />
              <YAxis tick={{ fontSize: 11 }} />
              <Tooltip />
              <Area type="monotone" dataKey="deger" stroke="#378ADD" fill="rgba(55,138,221,0.1)" strokeWidth={2} name="Araç/saat" />
            </AreaChart>
          </ResponsiveContainer>
        </div>
        <div className="card">
          <div className="card-title">Günlük enerji tüketimi</div>
          <div className="card-sub">Kavşak bazlı — kWh</div>
          <ResponsiveContainer width="100%" height={220}>
            <BarChart data={enerjiData}>
              <CartesianGrid strokeDasharray="3 3" stroke="#f1f5f9" />
              <XAxis dataKey="kavsak" tick={{ fontSize: 11 }} />
              <YAxis tick={{ fontSize: 11 }} />
              <Tooltip />
              <Bar dataKey="deger" fill="#378ADD" radius={[4,4,0,0]} name="kWh" />
            </BarChart>
          </ResponsiveContainer>
        </div>
      </div>
    </div>
  )
}
