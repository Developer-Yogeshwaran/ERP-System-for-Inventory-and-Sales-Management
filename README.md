# ERP System (Spring Boot + React)

This repository is a starter **ERP system** implementation that includes:

- **Backend:** Java 17, Spring Boot REST API, Spring Data JPA (Hibernate), Spring Security + JWT, Swagger/OpenAPI
- **Frontend:** React 18, React Router v6, Material UI, Axios, React Hook Form + Yup validation

## What’s Included

✅ Authentication (JWT + role-based access)

✅ Core domain models and APIs:
- Products
- Customers
- Users & roles

✅ Initial role setup (Admin/Sales/Purchase/Inventory/Accountant)

✅ Basic React UI + login + dashboard layout

---

## Getting Started (Local)

### Backend (Spring Boot)

```bash
cd backend
mvn spring-boot:run
```

The backend will run on `http://localhost:8080`.

Swagger UI is available at:

- `http://localhost:8080/swagger-ui.html`

### Frontend (React)

```bash
cd frontend
npm install
npm start
```

The frontend will run on `http://localhost:3000`.

---

## GitHub Repository Setup (push from local)

1. Initialize local git (if not already):

```bash
git init
```

2. Add remote (replace with your GitHub repo URL):

```bash
git remote add origin https://github.com/Developer-Yogeshwaran/ERP-System-for-Inventory-and-Sales-Management.git
```

3. Add + commit:

```bash
git add .
git commit -m "Initial ERP scaffold: backend + frontend"
```

4. Push to GitHub:

```bash
git push -u origin main
```

> **Note:** If your default branch is `master`, change `main` to `master`.

---

## Next Steps (Recommended)

- Implement a full sales order / purchase order / GRN module
- Add invoice generation & financial reporting
- Improve frontend navigation + role-based UI
- Add full integration tests (JUnit + React Testing Library)
