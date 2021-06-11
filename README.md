# project-registry-backend
Spring backend for Project Registry 

# Configuration for Checkstyle Plugin

To facilitate ease of development according to the enforced styleguide, please make the following changes within Eclipse (if needed)

1. Install `Checkstyle` Plugin
    - Go to `Help > Eclipse Marketplace...`
    - Search for `Checkstyle`
    - Install `Checkstyle Plug-in`
    - Restart Eclipse
    - Note that Eclipse will often detect this plugin as required and prompt you to install automatically after the project is imported
2. Import `Existing Maven Project` into Eclipse for this application
3. Change Formatter to use spaces instead of tabs
    - Click `Window > Preferences`
    - Expand `Java > Code Style > Formatter`
    - Click `Edit`
    - Expand `Indentation`
    - Change `Tab Policy` to `Spaces Only`
      - Verify that `Indentation size` and `Tab size` are both set to 4
    - Optionally, you can change the profile name
      - This will create a new Formatter profile to make it easy to swap back to tabs for other projects
    - Click `OK` and `Apply and Close`

Now you should be able to use `Ctrl + Shift + F` and `Ctrl + Shift + O` to format the style and organize the imports according to the enforced style guide.

# Configuration for GitHub Actions Workflow

There are a variety of secrets that must be configured on the repository for the GitHub Actions Workflow to complete successfully.

On the repository, navigate to `Settings > Secrets` and configure the below secrets

- `ECR_REGISTRY`
  - This is the docker repository name for Revature's ECR Account
  - It is of the form `<aws_account_id>.dkr.ecr.region.amazonaws.com`
- `ECR_REPOSITORY`
  - This is the name of the ECR repository which houses this service's images
  - They are often named corresponding to the trainer's name and batch id
- `SONAR_TOKEN`
  - This is the credential token to submit analysis reports to sonarcloud
- `AWS_REGION`
  - The region where the cloud resources are provisioned
  - Generally `us-east-1`
- `ECR_ACCESS_KEY_ID` and `ECR_SECRET_ACCESS_KEY`
  - The credentials to access ECR
  - Provided by Center of Excellence
- `EKS_ACCESS_KEY_ID` and `EKS_SECRET_ACCESS_KEY`
  - The credentials to access EKS
  - Provided by Center of Excellence
- `CLUSTER_NAME`
  - The name of the EKS cluster provisioned for the project
  - Generally named similarly to the `ECR_REPOSITORY`
  - Provided by Center of Excellence
- `APP_NAME`
  - The identifier for this service
  - Primarily for use within the Kubernetes Cluster
  - `project-registry-backend` for this service

Note that `GITHUB_TOKEN` does not need to be created.

# Ingress Configuration

To deploy ingress onto your cluster, leverage `helm`.

Once helm is installed, you may use the following commands:

1. `helm repo add ingress-nginx https://kubernetes.github.io/ingress-nginx`
2. `helm repo update`
3. `helm install ingress ingress-nginx/ingress-nginx`

To remove ingress, you can now simply run `helm uninstall ingress`

# Deploy locally

You can test the service locally by simply running `kubectl apply -f manifests/`