# Build and package the app with Maven
mvn clean package docker:build

# Tag the Docker image
docker tag budget-tracker:latest gcr.io/personal-projects-416300/budget-tracker:latest

# Push the Docker image to Google Container Registry (GCR)
docker push gcr.io/personal-projects-416300/budget-tracker:latest

# Deploy the app to Cloud Run
gcloud run deploy budget-tracker `
  --image gcr.io/personal-projects-416300/budget-tracker:latest `
  --platform managed `
  --region us-central1 `
  --allow-unauthenticated `
  --cpu 1 `
  --memory 1Gi `
  --port=8070 `
  --project=personal-projects-416300
