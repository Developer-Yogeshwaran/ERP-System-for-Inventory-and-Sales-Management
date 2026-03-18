# ERP System (Spring Boot + React)

This repository is a full-stack ERP starter template designed to support a mid-sized distribution and trading company.
It provides the core foundation for managing **products, customers, inventory, sales and purchases**, with **role-based access**, **JWT auth**, and a **React UI**.

---

## 🚀 Project Overview (From Scratch → Final)

### 1) Requirements (from scratch)
- Secure REST API with JWT authentication and role-based permissions
- CRUD operations for Products and Customers
- Inventory tracking + stock updates via receipts (GRNs)
- Order management (sales + purchase) + invoice generation
- Web UI with authentication, user roles, dashboards, and reports

### 2) Backend Implementation (Spring Boot)
- Created a **Spring Boot** project with JPA (Hibernate) and H2 (in-memory) default DB
- Implemented **JWT-based security** (login/signup + token refresh patterns)
- Added **role management** + seeded default roles and an admin user on startup
- Built API endpoints for core entities (`/api/products`, `/api/customers`, `/api/auth`)
- Added Swagger UI for API documentation (`/swagger-ui.html`)

### 3) Frontend Implementation (React)
- Scaffolded a React app with **React Router v6**, **Material UI**, and **Axios**
- Built a **login page** and a simple **dashboard** page
- Stored JWT token in `localStorage` and added basic route protection

### 4) Final Product State
- Fully working scaffold with authentication + role-based access
- CRUD APIs for products and customers
- React UI capable of logging in and viewing a dashboard
- GitHub repo push included with setup commands and structure docs

---

## 🧰 Tech Stack

| Layer | Technology |
|------|------------|
| Backend | Java 17, Spring Boot, Spring Data JPA, Spring Security, JWT, Swagger/OpenAPI |
| Database | H2 (default), MySQL (config ready) |
| Frontend | React 18, React Router v6, Material UI, Axios, React Hook Form + Yup |
| Testing | JUnit + Mockito (backend), Jest + React Testing Library (frontend) |

---

## 📁 Project Structure

```
/ (root)
├─ backend/                          # Spring Boot REST API
│  ├─ src/main/java/com/example/erp
│  │  ├─ controller/                 # REST controllers
│  │  ├─ model/                      # JPA entities
│  │  ├─ payload/                    # request/response DTOs
│  │  ├─ repository/                 # Spring Data repositories
│  │  ├─ security/                   # JWT + security config
│  │  └─ ErpApplication.java         # main app + data seeding
│  └─ src/main/resources
│     └─ application.yml             # app config + JWT secrets
└─ frontend/                         # React UI
   ├─ public/
   └─ src/
      ├─ pages/                      # page components (Login, Dashboard)
      ├─ App.js                      # router + layout
      └─ index.js                    # app bootstrap
```

---

## ✅ Getting Started (Local)

### Backend

```bash
cd backend
mvn spring-boot:run
```

- Runs on: `http://localhost:8080`
- Swagger UI: `http://localhost:8080/swagger-ui.html`

### Frontend

```bash
cd frontend
npm install
npm start
```

- Runs on: `http://localhost:3000`

---

## 🔐 Default Admin Login

A default admin user is seeded on startup:

- **username:** `admin`
- **password:** `admin123`

> Change these before production.

---

## 🧩 Pushing to GitHub (already done)

If you need to re-run or re-configure the repo remote:

```bash
git init
git remote add origin https://github.com/Developer-Yogeshwaran/ERP-System-for-Inventory-and-Sales-Management.git
git add .
git commit -m "Initial ERP scaffold"
git push -u origin master
```

---

## 🔭 Next Improvements

✅ Add full Order / Purchase / GRN / Invoice modules

✅ Implement stock update logic (inventory changes when orders/GRNs are created)

✅ Build reporting dashboard (sales metrics, stock valuation, financials)

✅ Add full authentication flow (refresh tokens, logout, role-scoped UI)

✅ Add end-to-end tests and CI pipeline
