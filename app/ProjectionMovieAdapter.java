public class ProjectionMovieAdapter extends RecyclerView.Adapter<ProjectionMovieAdapter.ViewHolder> {
    private List<Movie> movies;
    // Add any additional fields or methods you need

    public ProjectionMovieAdapter(List<Movie> movies) {
        this.movies = movies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_projection_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie movie = movies.get(position);
        // Bind the movie data to the views in the ViewHolder
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Declare the necessary views and widgets for each item

        public ViewHolder(View itemView) {
            super(itemView);
            // Initialize the views and widgets

            // Set up click listener for the item view if needed
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = itemView.getContext();
                    Intent intent = new Intent(context, ProjectionActivity.class);
                    // Pass any necessary data to the ProjectionActivity using intent extras
                    context.startActivity(intent);
                }
            });
        }
    }

    // Add any additional methods you need
}
