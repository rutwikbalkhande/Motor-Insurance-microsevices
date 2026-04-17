# write this prometheus.yml file in file explore that collect application matrix in time serise form.
#install grafana & Prometheus server on our pc.

global:
scrape_interval: 15s
evaluation_interval: 15s

alerting:
alertmanagers:
- static_configs:
- targets: []

rule_files: []

scrape_configs:
- job_name: "prometheus"
  static_configs:
    - targets: ["localhost:9090"]
      labels:
      app: "prometheus"

- job_name: "springboot-app"
  metrics_path: "/actuator/prometheus"
  static_configs:
    - targets: ["localhost:8080"]
      labels:
      app: "prometheus_grafana"

- job_name: 'user-service'
  metrics_path: '/actuator/prometheus'
  static_configs:
    - targets: ['localhost:8081']

- job_name: 'drivinghistory-service'
  metrics_path: '/actuator/prometheus'
  static_configs:
    - targets: ['localhost:8082']

- job_name: 'policy-service'
  metrics_path: '/actuator/prometheus'
  static_configs:
    - targets: ['localhost:8083']

- job_name: 'claim-service'
  metrics_path: '/actuator/prometheus'
  static_configs:
    - targets: ['localhost:8084']

- job_name: 'API-Gateway'
  metrics_path: '/actuator/prometheus'
  static_configs:
    - targets: ['localhost:8085']

- job_name: 'service-registry'
  metrics_path: '/actuator/prometheus'
  static_configs:
    - targets: ['localhost:8761']
